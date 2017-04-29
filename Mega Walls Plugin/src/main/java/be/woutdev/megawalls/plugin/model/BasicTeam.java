package be.woutdev.megawalls.plugin.model;

import be.woutdev.megawalls.api.enums.TeamColor;
import be.woutdev.megawalls.api.model.Game;
import be.woutdev.megawalls.api.model.Team;
import be.woutdev.megawalls.api.model.User;
import be.woutdev.megawalls.api.model.Wither;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wout on 19/08/2016.
 */
@Entity
@Table(name = "mw_teams")
public class BasicTeam implements Team
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @ManyToOne
    private Game game;
    @Column
    private TeamColor color;
    @Column
    private double witherHealth;
    @Column
    @OneToMany
    private Set<User> players;
    @Transient
    private Wither wither;
    @Version
    private Long lastUpdated;

    public BasicTeam(Game game, TeamColor color)
    {
        this.game = game;
        this.color = color;
        this.witherHealth = 1000;
        this.players = new HashSet<>();
    }

    @Override
    public TeamColor getTeamColor()
    {
        return color;
    }

    @Override
    public Game getGame()
    {
        return game;
    }

    @Override
    public Wither getWither()
    {
        return wither;
    }

    @Override
    public Set<User> getPlayers()
    {
        return players;
    }

    public void setWither(Wither wither)
    {
        this.wither = wither;
    }

    public Long getLastUpdated()
    {
        return lastUpdated;
    }

    public int getId()
    {
        return id;
    }

    public void setWitherHealth(double witherHealth)
    {
        this.witherHealth = witherHealth;
    }
}
