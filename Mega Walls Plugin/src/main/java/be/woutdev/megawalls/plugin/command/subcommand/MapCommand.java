package be.woutdev.megawalls.plugin.command.subcommand;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.enums.TeamColor;
import be.woutdev.megawalls.plugin.model.BasicMap;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Wout on 19/08/2016.
 */
public class MapCommand extends SubCommand
{
    public MapCommand(CommandSender sender, String[] args)
    {
        super(sender, 2, 3, true, true,
              "/mw map <create, delete, setspawn, setwither, setlobby, enable, disable, check> <mapname> [teamcolor]",
              "command.map", args);
    }

    @Override
    public boolean execute()
    {
        boolean result = super.execute();

        if (!result)
        {
            return false;
        }

        String action = args[0].toLowerCase();

        switch (action)
        {
            case "create":
                handleCreate(args);
                break;
            case "delete":
                handleDelete(args);
                break;
            case "setspawn":
                handleSetSpawn(args);
                break;
            case "setwither":
                handleSetWither(args);
                break;
            case "setlobby":
                handleSetLobby(args);
                break;
            case "enable":
                handleEnable(args);
                break;
            case "disable":
                handleDisable(args);
                break;
            case "check":
                handleCheck(args);
                break;
            default:
                showUsage();
                break;
        }

        return true;
    }

    private void handleCheck(String[] args)
    {
        BasicMap map = (BasicMap) MWAPI.getMapHandler().findByName(args[1]);

        if (map == null)
        {
            sender.sendMessage(ChatColor.RED + "No map with name " + args[1] + ".");
            return;
        }

        if (map.isConfigured())
        {
            sender.sendMessage(ChatColor.GOLD + "This map is currently configured for " + ChatColor.AQUA +
                               map.getAvailableTeams().size() + ChatColor.GOLD + " teams.");
        }
        else
        {
            sender.sendMessage(ChatColor.GOLD + "This map is not configured yet. Note(s):");

            if (map.getLobbyLocation() == null || map.getLobbyLocation().getWorld() == null ||
                map.getLobbyLocation().getWorld().getName().equals(""))
            {
                sender.sendMessage(ChatColor.YELLOW + "- The lobby location is not set.");
            }

            if (map.getAvailableTeams().size() < 2)
            {
                sender.sendMessage(ChatColor.YELLOW + "- You must at least configure 2 teams, only " + ChatColor.AQUA +
                                   map.getAvailableTeams().size() + ChatColor.YELLOW + " are configured.");
            }

            if (MWAPI.getPlugin().getConfig().getConfigurationSection("settings.maps." + map.getName()) == null)
            {
                sender.sendMessage(ChatColor.YELLOW +
                                   "- There is currently no specific map configuration. Edit the config.yml and make sure the correct configuration section for this map exists and is valid.");
            }
        }
    }

    private void handleDisable(String[] args)
    {
        BasicMap map = (BasicMap) MWAPI.getMapHandler().findByName(args[1]);

        if (map == null)
        {
            sender.sendMessage(ChatColor.RED + "No map with name " + args[1] + ".");
            return;
        }

        map.setActive(false);
        map.save();

        sender.sendMessage(
                ChatColor.GOLD + "Map " + ChatColor.AQUA + args[1] + ChatColor.GOLD + " is now " + ChatColor.RED +
                "disabled" + ChatColor.GOLD + ".");
    }

    private void handleEnable(String[] args)
    {
        BasicMap map = (BasicMap) MWAPI.getMapHandler().findByName(args[1]);

        if (map == null)
        {
            sender.sendMessage(ChatColor.RED + "No map with name " + args[1] + ".");
            return;
        }

        map.setActive(true);
        map.save();

        sender.sendMessage(
                ChatColor.GOLD + "Map " + ChatColor.AQUA + args[1] + ChatColor.GOLD + " is now " + ChatColor.GREEN +
                "enabled" + ChatColor.GOLD + ".");

        if (!map.isConfigured())
        {
            sender.sendMessage(ChatColor.DARK_AQUA +
                               "Note: This map is not configured properly yet. For more information, run command " +
                               ChatColor.AQUA + "/mw map check " + args[1] + ChatColor.DARK_AQUA + ".");
        }
    }

    private void handleSetLobby(String[] args)
    {
        BasicMap map = (BasicMap) MWAPI.getMapHandler().findByName(args[1]);

        if (map == null)
        {
            sender.sendMessage(ChatColor.RED + "No map with name " + args[1] + ".");
            return;
        }

        map.setLobbyLocation(((Player) sender).getLocation());
        map.save();

        sender.sendMessage(
                ChatColor.GOLD + "Lobby location for map " + ChatColor.AQUA + args[1] + ChatColor.GOLD + " set.");
    }

    private void handleSetWither(String[] args)
    {
        BasicMap map = (BasicMap) MWAPI.getMapHandler().findByName(args[1]);

        if (map == null)
        {
            sender.sendMessage(ChatColor.RED + "No map with name " + args[1] + ".");
            return;
        }

        if (args.length != 3)
        {
            sender.sendMessage(ChatColor.RED + "Please specify team color.");
            return;
        }

        TeamColor color = TeamColor.findByName(args[2]);

        if (color == null)
        {
            sender.sendMessage(ChatColor.RED + "The color " + args[2] + " does not exist.");
            return;
        }

        map.getTeamWitherLocations().put(color, ((Player) sender).getLocation());
        map.save();

        sender.sendMessage(ChatColor.GOLD + "Wither location set.");
    }

    private void handleSetSpawn(String[] args)
    {
        BasicMap map = (BasicMap) MWAPI.getMapHandler().findByName(args[1]);

        if (map == null)
        {
            sender.sendMessage(ChatColor.RED + "No map with name " + args[1] + ".");
            return;
        }

        if (args.length != 3)
        {
            sender.sendMessage(ChatColor.RED + "Please specify team color.");
            return;
        }

        TeamColor color = TeamColor.findByName(args[2]);

        if (color == null)
        {
            sender.sendMessage(ChatColor.RED + "The color " + args[2] + " does not exist.");
            return;
        }

        map.getTeamSpawnLocations().put(color, ((Player) sender).getLocation());
        map.save();

        sender.sendMessage(ChatColor.GOLD + "Spawn location set.");
    }

    private void handleDelete(String[] args)
    {
        boolean result = MWAPI.getMapHandler().delete(args[1]);

        if (result)
        {
            sender.sendMessage(
                    ChatColor.GOLD + "Successfully deleted map " + ChatColor.AQUA + args[1] + ChatColor.GOLD + ".");
        }
        else
        {
            sender.sendMessage(ChatColor.RED + "Failed to delete map " + args[1] +
                               ". No games may be active, so make sure to disable the map first.");
        }
    }

    private void handleCreate(String[] args)
    {
        boolean result = MWAPI.getMapHandler().create(args[1]);

        if (result)
        {
            sender.sendMessage(
                    ChatColor.GOLD + "Successfully created map " + ChatColor.AQUA + args[1] + ChatColor.GOLD +
                    ". The map will be unavailable until completely set up.");
        }
        else
        {
            sender.sendMessage(ChatColor.RED + "Failed to create map " + args[1] + ".");
        }
    }
}
