package be.woutdev.megawalls.plugin.command.subcommand;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.enums.GameState;
import be.woutdev.megawalls.api.model.Game;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wout on 21/08/2016.
 */
public class GameListCommand extends SubCommand
{
    public GameListCommand(CommandSender sender, String[] args)
    {
        super(sender, 0, 0, false, false, "", "command.gamelist", args);
    }

    @Override
    public boolean execute()
    {
        boolean result = super.execute();

        if (!result)
        {
            return false;
        }

        Set<Game> activeGames = new HashSet<>(MWAPI.getGameHandler().findAll());
        activeGames.removeAll(MWAPI.getGameHandler().findByState(GameState.ENDING));

        if (activeGames.size() == 0)
        {
            sender.sendMessage(ChatColor.RED + "There are no active games at this moment.");
            return true;
        }

        sender.sendMessage(
                ChatColor.GOLD + "There are currently " + ChatColor.AQUA + activeGames.size() + ChatColor.GOLD +
                " active games.");

        sender.sendMessage(ChatColor.GOLD + "ID     |     MAP          |          STATE");

        for (Game game : activeGames)
        {
            int idSize = String.valueOf(game.getId()).length();
            int mapNameSize = String.valueOf(game.getMap().getName()).length();

            sender.sendMessage(ChatColor.AQUA + "" + game.getId() + StringUtils.repeat(" ", (12 - idSize)) +
                               game.getMap().getName().toUpperCase() + StringUtils.repeat(" ", (23 - mapNameSize)) +
                               game.getState().name());
        }

        return true;
    }
}
