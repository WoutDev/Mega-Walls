package be.woutdev.megawalls.api.model;

/**
 * Created by Wout on 22/08/2016.
 */
public interface Wither
{
    Team getTeam();

    double getHealth();

    boolean isDead();
}
