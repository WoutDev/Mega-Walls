package be.woutdev.megawalls.plugin.command.subcommand;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.model.Map;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Set;

/**
 * Created by Wout on 21/08/2016.
 */
public class MapListCommand extends SubCommand
{
    public MapListCommand(CommandSender sender, String[] args)
    {
        super(sender, 0, 0, false, false, "", "command.maplist", args);
    }

    @Override
    public boolean execute()
    {
        boolean result = super.execute();

        if (!result)
        {
            return false;
        }

        Set<Map> maps = MWAPI.getMapHandler().findAll();

        if (maps.size() == 0)
        {
            sender.sendMessage(ChatColor.RED + "There are no maps created.");
            return false;
        }

        StringBuilder builder = new StringBuilder();

        for (Map map : maps)
        {
            if (map.isConfigured() && map.isActive())
            {
                builder.append(ChatColor.GREEN).append(map.getName()).append(ChatColor.YELLOW).append(", ");
            }
            else
            {
                builder.append(ChatColor.RED).append(map.getName()).append(ChatColor.YELLOW).append(", ");
            }
        }

        String mapsString = builder.toString();

        if (mapsString.endsWith(", "))
        {
            mapsString = mapsString.substring(0, mapsString.length() - 2);
        }

        sender.sendMessage(ChatColor.GOLD + "There is a total of " + maps.size() + " map(s).");
        sender.sendMessage(mapsString);

        return true;
    }
}
