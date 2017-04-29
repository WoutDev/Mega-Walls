package be.woutdev.megawalls.plugin.model;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.enums.GameState;
import be.woutdev.megawalls.api.enums.TeamColor;
import be.woutdev.megawalls.api.model.Game;
import be.woutdev.megawalls.api.model.Map;
import be.woutdev.megawalls.api.model.Team;
import be.woutdev.megawalls.api.model.User;
import be.woutdev.megawalls.plugin.handler.game.BasicGameHandler;
import be.woutdev.megawalls.plugin.helper.EntityHider;
import be.woutdev.megawalls.plugin.helper.ItemHelper;
import be.woutdev.megawalls.plugin.helper.ScoreboardHelper;
import be.woutdev.megawalls.plugin.thread.GameStartingRunnable;
import be.woutdev.megawalls.plugin.wither.NMSWither;
import org.bukkit.ChatColor;
import org.bukkit.Location;

import javax.persistence.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Stream;

/**
 * Created by Wout on 19/08/2016.
 */
@Entity
@Table(name = "mw_games")
public class BasicGame implements Game
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private Map map;
    @Column
    @OneToMany(mappedBy = "game")
    private Set<User> spectators;
    @Column
    @OneToMany(mappedBy = "game")
    private Set<Team> teams;
    @Column
    @OneToMany(mappedBy = "game")
    private Set<User> usersInLobby;
    @Column
    private GameState state;
    @Column
    private Date startedOn;
    @Column
    private Date endedOn;
    @Column
    private TeamColor winner;
    @Transient
    private EntityHider entityHider;
    @Transient
    private GameStartingRunnable gameStartingRunnable;
    @Version
    private Long lastUpdate;

    public BasicGame(Map map)
    {
        this.map = map;
        this.spectators = new HashSet<User>();
        this.teams = new HashSet<>();
        this.usersInLobby = new CopyOnWriteArraySet<>();

        for (TeamColor color : map.getAvailableTeams())
        {
            teams.add(new BasicTeam(this, color));
        }

        this.entityHider = new EntityHider(MWAPI.getPlugin(), EntityHider.Policy.BLACKLIST);
        this.gameStartingRunnable = new GameStartingRunnable(this);

        this.startedOn = null;
        this.endedOn = null;
        this.winner = null;

        this.state = GameState.LOBBY;
    }

    public Long getLastUpdate()
    {
        return lastUpdate;
    }

    @Override
    public int getId()
    {
        return id;
    }

    @Override
    public Map getMap()
    {
        return map;
    }

    @Override
    public GameState getState()
    {
        return state;
    }

    @Override
    public Set<User> getSpectators()
    {
        return spectators;
    }

    @Override
    public Set<Team> getTeams()
    {
        return teams;
    }

    @Override
    public Date getStartedAt()
    {
        return startedOn;
    }

    @Override
    public Date getEndedAt()
    {
        return endedOn;
    }

    @Override
    public TeamColor getWinner()
    {
        return winner;
    }

    @Override
    public boolean join(User user)
    {
        if (getState().equals(GameState.LOBBY))
        {
            if (usersInLobby.contains(user))
            {
                return false;
            }

            if (usersInLobby.size() >= getMap().getAvailableTeams().size() * MWAPI.getConfig().getMaxTeamSize(getMap()))
            {
                return false;
            }

            MWAPI.getTeleportHandler().teleport(user, map.getLobbyLocation());
            user.getPlayer().setHealth(20);
            user.getPlayer().setFoodLevel(20);
            user.getPlayer().getInventory().clear();

            user.getPlayer().getInventory().setItem(0, ItemHelper.getKitSelector());

            if (MWAPI.getConfig().isTeamSelectionEnabled())
            {
                user.getPlayer().getInventory().setItem(4, ItemHelper.getTeamSelector());
            }

            user.getPlayer().getInventory().setItem(8, ItemHelper.getLeaveItem());

            usersInLobby.add(user);
            ((BasicUser) user).setGame(this);

            if (usersInLobby.size() >=
                getMap().getAvailableTeams().size() * MWAPI.getConfig().getMinTeamSize(getMap()) &&
                !getGameStartingRunnable().isStarted())
            {
                getGameStartingRunnable().runTaskTimerAsynchronously(MWAPI.getPlugin(), 20L, 20L);

                for (User u : usersInLobby)
                {
                    ScoreboardHelper.setCountdownLobbyScoreboard((BasicUser) u);
                }
            }

            if (!getGameStartingRunnable().isStarted())
            {
                ScoreboardHelper.setWaitingLobbyScoreboard((BasicUser) user);
            }

            return true;
        }

        return false;
    }

    @Override
    public boolean leave(User user)
    {
        if (getState().equals(GameState.LOBBY))
        {
            if (usersInLobby.contains(user))
            {
                usersInLobby.remove(user);
            }
            else
            {
                return false;
            }
        }
        else
        {
            Team team = getTeam(user);

            if (team != null)
            {
                team.getPlayers().remove(user);
                ((BasicUser) user).setTeam(null);
            }
            else
            {
                return false;
            }
        }

        MWAPI.getTeleportHandler().teleport(user, user.getPlayer().getLocation().getWorld().getSpawnLocation());

        user.getPlayer().getInventory().clear();

        ((BasicUser) user).setGame(null);
        ((BasicUser) user).getScoreboard().stop();
        ((BasicUser) user).setScoreboard(null);

        return true;
    }

    @Override
    public boolean spectate(User user)
    {
        if (!getState().equals(GameState.LOBBY) && !getState().equals(GameState.ENDED))
        {
            // TODO
            return true;
        }

        return false;
    }

    @Override
    public boolean isPlaying(User user)
    {
        return usersInLobby.contains(user) || getTeam(user) != null;
    }

    @Override
    public Team getTeam(User user)
    {
        return teams.stream().filter(t -> t.getPlayers().contains(user)).findAny().orElse(null);
    }

    @Override
    public void end(TeamColor winner)
    {
        if (winner != null)
        {
            // TODO end game with winner 'winner'
        }
        else
        {
            // TODO end game with draw
        }
    }

    @Override
    public void start()
    {
        for (Team team : getTeams())
        {
            BasicWither wither = new BasicWither(team);
            wither.setWither(getNMSWither(team));

            ((BasicTeam) team).setWither(wither);

            wither.getWither().spawn();
        }

        for (User user : getLobbyPlayers())
        {
            if (user.getTeam() == null)
            {
                BasicTeam team = (BasicTeam) getBalancedTeam();
                ((BasicUser) user).setTeam(team);
                team.getPlayers().add(user);
            }

            MWAPI.getTeleportHandler().teleport(user, map.getSpawnLocation(user.getTeam().getTeamColor()));

            // TODO: Give kit

            user.getPlayer()
                .sendMessage(ChatColor.YELLOW + "You are playing in team " +
                             user.getTeam().getTeamColor().name().toLowerCase() +
                             ". Gather resources and prepare yourself before the walls come down!");
        }
    }

    @Override
    public boolean isSpectating(User user)
    {
        return spectators.contains(user);
    }

    @Override
    public Set<User> getPlayers()
    {
        HashSet<User> users = new HashSet<>();

        teams.forEach(t -> users.addAll(t.getPlayers()));

        return users;
    }

    @Override
    public void sendMessage(String message)
    {
        Stream.concat(getSpectators().stream(), getPlayers().stream())
              .forEach(u -> u.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', message)));
    }

    public void setId(int id)
    {
        this.id = id;
    }

    private Team getBalancedTeam()
    {
        return teams.stream().sorted(Comparator.comparingInt((Team t) -> t.getPlayers().size())).findFirst().get();
    }

    public void setEndedOn(Date endedOn)
    {
        this.endedOn = endedOn;
    }

    public void setStartedOn(Date startedOn)
    {
        this.startedOn = startedOn;
    }

    public EntityHider getEntityHider()
    {
        return entityHider;
    }

    public GameStartingRunnable getGameStartingRunnable()
    {
        return gameStartingRunnable;
    }

    public Set<User> getLobbyPlayers()
    {
        return usersInLobby;
    }

    private NMSWither getNMSWither(Team team)
    {
        try
        {
            return (NMSWither) ((BasicGameHandler) MWAPI.getGameHandler()).getWitherType()
                                                                          .getConstructor(Team.class, Location.class)
                                                                          .newInstance(team,
                                                                                       map.getWitherLocation(
                                                                                               team.getTeamColor()));
        }
        catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
