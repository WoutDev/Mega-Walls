package be.woutdev.megawalls.plugin.model;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.model.Team;
import be.woutdev.megawalls.api.model.Wither;
import be.woutdev.megawalls.plugin.wither.NMSWither;

/**
 * Created by Wout on 22/08/2016.
 */
public class BasicWither implements Wither
{
    private final Team team;
    private double health;
    private NMSWither wither;
    private boolean dead;

    public BasicWither(Team team)
    {
        this.team = team;
        this.health = MWAPI.getConfig().getDefaultWitherHealth(team.getGame().getMap());
        this.dead = false;
    }

    @Override
    public Team getTeam()
    {
        return team;
    }

    @Override
    public double getHealth()
    {
        return health;
    }

    @Override
    public boolean isDead()
    {
        return dead;
    }

    public void setDead(boolean dead)
    {
        this.dead = dead;
    }

    public void setHealth(double health)
    {
        this.health = health;
        ((BasicTeam) team).setWitherHealth(health);
    }

    public NMSWither getWither()
    {
        return wither;
    }

    public void setWither(NMSWither wither)
    {
        this.wither = wither;
    }
}
