package be.woutdev.megawalls.plugin.model;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.enums.TeamColor;
import be.woutdev.megawalls.api.model.Map;
import be.woutdev.megawalls.plugin.handler.BasicMapHandler;
import com.google.common.collect.Lists;
import org.bukkit.Location;

import java.util.EnumMap;
import java.util.List;

/**
 * Created by Wout on 19/08/2016.
 */
public class BasicMap implements Map
{
    private String name;
    private boolean active;
    private Location lobbyLocation;
    private EnumMap<TeamColor, Location> teamSpawnLocations;
    private EnumMap<TeamColor, Location> teamWitherLocations;

    public BasicMap(String name)
    {
        this.name = name;
        this.active = false;
        this.lobbyLocation = null;
        this.teamSpawnLocations = new EnumMap<TeamColor, Location>(TeamColor.class);
        this.teamWitherLocations = new EnumMap<TeamColor, Location>(TeamColor.class);
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public Location getLobbyLocation()
    {
        return lobbyLocation;
    }

    @Override
    public Location getSpawnLocation(TeamColor team)
    {
        return teamSpawnLocations.get(team);
    }

    @Override
    public Location getWitherLocation(TeamColor team)
    {
        return teamWitherLocations.get(team);
    }

    @Override
    public List<TeamColor> getAvailableTeams()
    {
        List<TeamColor> availableTeams = Lists.newArrayList();

        for (TeamColor color : TeamColor.values())
        {
            if (teamSpawnLocations.containsKey(color) && teamWitherLocations.containsKey(color))
            {
                availableTeams.add(color);
            }
        }

        return availableTeams;
    }

    @Override
    public boolean isActive()
    {
        return active;
    }

    @Override
    public boolean isConfigured()
    {
        return getAvailableTeams().size() >= 2 && lobbyLocation != null && lobbyLocation.getWorld() != null &&
               !lobbyLocation.getWorld().getName().equalsIgnoreCase("") &&
               MWAPI.getPlugin().getConfig().getConfigurationSection("settings.maps." + getName()) != null;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public void setLobbyLocation(Location lobbyLocation)
    {
        this.lobbyLocation = lobbyLocation;
    }

    public EnumMap<TeamColor, Location> getTeamSpawnLocations()
    {
        return teamSpawnLocations;
    }

    public EnumMap<TeamColor, Location> getTeamWitherLocations()
    {
        return teamWitherLocations;
    }

    public void save()
    {
        ((BasicMapHandler) MWAPI.getMapHandler()).update(this);
    }
}
