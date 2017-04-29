package be.woutdev.megawalls.plugin.handler.team;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.enums.TeamColor;
import be.woutdev.megawalls.api.handler.TeamHandler;
import be.woutdev.megawalls.api.model.Game;
import be.woutdev.megawalls.api.model.Team;
import be.woutdev.megawalls.plugin.model.BasicTeam;

/**
 * Created by Wout on 20/08/2016.
 */
public class BasicTeamHandler implements TeamHandler
{
    @Override
    public Team findById(int id)
    {
        for (Game game : MWAPI.getGameHandler().findAll())
        {
            for (Team team : game.getTeams())
            {
                if (((BasicTeam) team).getId() == id)
                {
                    return team;
                }
            }
        }

        return null;
    }

    @Override
    public Team findByColor(Game game, TeamColor color)
    {
        return game.getTeams().stream().filter(t -> t.getTeamColor().equals(color)).findAny().orElse(null);
    }
}
