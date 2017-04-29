package be.woutdev.megawalls.api.model;

import be.woutdev.megawalls.api.enums.TeamColor;
import org.bukkit.Location;

import java.util.List;

/**
 * Created by Wout on 19/08/2016.
 */
public interface Map
{
    String getName();

    Location getLobbyLocation();

    Location getSpawnLocation(TeamColor team);

    Location getWitherLocation(TeamColor team);

    List<TeamColor> getAvailableTeams();

    boolean isActive();

    boolean isConfigured();
}
