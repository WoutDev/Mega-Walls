package be.woutdev.megawalls.plugin.command.subcommand;

import org.bukkit.command.CommandSender;

/**
 * Created by Wout on 19/08/2016.
 */
public class UserCommand extends SubCommand
{
    public UserCommand(CommandSender sender, String[] args)
    {
        super(sender, 0, 0, false, true, "/mw user <> []", "command.user", args);
    }

    @Override
    public boolean execute()
    {
        boolean result = super.execute();

        if (!result)
        {
            return false;
        }

        // TODO

        return true;
    }
}
