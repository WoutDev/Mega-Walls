package be.woutdev.megawalls.api.model;

import be.woutdev.megawalls.api.enums.TeamColor;

import java.util.Set;

/**
 * Created by Wout on 19/08/2016.
 */
public interface Team
{
    TeamColor getTeamColor();

    Game getGame();

    Wither getWither();

    Set<User> getPlayers();
}
