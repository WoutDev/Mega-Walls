package be.woutdev.megawalls.api.handler;

import be.woutdev.megawalls.api.enums.TeamColor;
import be.woutdev.megawalls.api.model.Game;
import be.woutdev.megawalls.api.model.Team;

/**
 * Created by Wout on 20/08/2016.
 */
public interface TeamHandler
{
    Team findById(int id);

    Team findByColor(Game game, TeamColor color);
}
