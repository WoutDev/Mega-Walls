package be.woutdev.megawalls.plugin.model;

import be.woutdev.megawalls.api.kit.Kit;
import be.woutdev.megawalls.api.model.Game;
import be.woutdev.megawalls.api.model.Team;
import be.woutdev.megawalls.api.model.User;
import be.woutdev.megawalls.plugin.scoreboard.UserScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by Wout on 19/08/2016.
 */
@Entity
@Table(name = "mw_users")
public class BasicUser implements User
{
    @Id
    private UUID uniqueId;
    @Column
    private boolean playing;
    @Column
    private boolean spectator;
    @Column
    @ManyToOne
    private Game game;
    @Column
    @ManyToOne
    private Team team;
    @Transient
    private Kit kit;
    @Transient
    private UserScoreboard scoreboard;
    @Version
    private Long lastUpdate;

    public BasicUser(UUID uniqueId)
    {
        this.uniqueId = uniqueId;
        this.playing = false;
        this.spectator = false;
        this.game = null;
        this.team = null;
        this.kit = null;
    }

    @Override
    public UUID getUniqueId()
    {
        return uniqueId;
    }

    @Override
    public boolean isPlaying()
    {
        return playing;
    }

    @Override
    public boolean isSpectating()
    {
        return spectator;
    }

    @Override
    public Game getGame()
    {
        return game;
    }

    @Override
    public Team getTeam()
    {
        return team;
    }

    @Override
    public Kit getKit()
    {
        return kit;
    }

    @Override
    public Player getPlayer()
    {
        return Bukkit.getPlayer(uniqueId);
    }

    public void setTeam(BasicTeam team)
    {
        this.team = team;
    }

    public void setGame(BasicGame game)
    {
        this.game = game;
    }

    public void setKit(Kit kit)
    {
        this.kit = kit;
    }

    public Long getLastUpdate()
    {
        return lastUpdate;
    }

    public UserScoreboard getScoreboard()
    {
        return scoreboard;
    }

    public void setScoreboard(UserScoreboard scoreboard)
    {
        this.scoreboard = scoreboard;
    }
}
