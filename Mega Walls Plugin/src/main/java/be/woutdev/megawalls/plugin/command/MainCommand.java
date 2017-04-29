package be.woutdev.megawalls.plugin.command;

import be.woutdev.megawalls.plugin.command.subcommand.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Wout on 19/08/2016.
 */
public class MainCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (args.length == 0)
        {
            sendUsage(sender);
            return false;
        }

        switch (args[0].toLowerCase())
        {
            case "map":
                new MapCommand(sender, args).execute();
                break;
            case "game":
                new GameCommand(sender, args).execute();
                break;
            case "user":
                new UserCommand(sender, args).execute();
                break;
            case "maplist":
                new MapListCommand(sender, args).execute();
                break;
            case "gamelist":
                new GameListCommand(sender, args).execute();
                break;
            default:
                sendUsage(sender);
                break;
        }

        return true;
    }

    private void sendUsage(CommandSender sender)
    {
        sender.sendMessage(ChatColor.RED + "Usage: /mw <map, game, user, maplist, gamelist> [...]");
    }
}
