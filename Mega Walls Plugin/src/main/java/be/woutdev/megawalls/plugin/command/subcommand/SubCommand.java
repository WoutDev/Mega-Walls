package be.woutdev.megawalls.plugin.command.subcommand;

import be.woutdev.megawalls.api.MWAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Wout on 19/08/2016.
 */
public abstract class SubCommand
{
    protected final CommandSender sender;
    private final int minArgs;
    private final int maxArgs;
    private final boolean playerOnly;
    private final boolean sub;
    private final String usage;
    private final String permission;
    protected String[] args;

    protected SubCommand(CommandSender sender, int minArgs, int maxArgs, boolean playerOnly, boolean sub, String usage, String permission, String[] args)
    {
        this.sender = sender;
        this.minArgs = minArgs;
        this.maxArgs = maxArgs;
        this.playerOnly = playerOnly;
        this.sub = sub;
        this.usage = usage;
        this.permission = permission;
        this.args = new String[args.length - 1];

        System.arraycopy(args, 1, this.args, 0, args.length - 1);
    }

    public boolean execute()
    {
        if (sender instanceof Player)
        {
            if (!MWAPI.getPermissionHelper().hasPermission((Player) sender, permission))
            {
                sender.sendMessage(ChatColor.RED + "You do not have permission to do this!");
                return false;
            }

            if (sub && args.length >= 1)
            {
                String sub = args[0];

                if (!MWAPI.getPermissionHelper().hasPermission((Player) sender, permission + "." + sub) &&
                    !MWAPI.getPermissionHelper().hasPermission((Player) sender, permission + ".*"))
                {
                    sender.sendMessage(ChatColor.RED + "You do not have permission to do this!");
                    return false;
                }
            }
        }
        else
        {
            if (playerOnly)
            {
                sender.sendMessage(ChatColor.RED + "This command is player only.");
                return false;
            }
        }

        if (args.length < minArgs || args.length > maxArgs)
        {
            showUsage();
            return false;
        }

        return true;
    }

    public void showUsage()
    {
        sender.sendMessage(ChatColor.RED + "Usage: " + usage);
    }
}
