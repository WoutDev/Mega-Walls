package be.woutdev.megawalls.plugin.command.subcommand;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.enums.GameState;
import be.woutdev.megawalls.api.model.Game;
import be.woutdev.megawalls.api.model.Map;
import be.woutdev.megawalls.api.model.User;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Wout on 19/08/2016.
 */
public class GameCommand extends SubCommand
{
    public GameCommand(CommandSender sender, String[] args)
    {
        super(sender, 2, 2, false, true, "/mw game <create, info, spectate, join, leave, end> <map/game>",
              "command.game", args);
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
            case "info":
                handleInfo(args);
                break;
            case "spectate":
                handleSpectate(args);
                break;
            case "join":
                handleJoin(args);
                break;
            case "leave":
                handleLeave(args);
                break;
            case "end":
                handleEnd(args);
                break;
            default:
                showUsage();
                break;
        }

        return true;
    }

    private void handleEnd(String[] args)
    {
        int id;
        try
        {
            id = Integer.parseInt(args[1]);
        }
        catch (Exception e)
        {
            sender.sendMessage(ChatColor.RED + "Game with id " + args[1] + " not found.");
            return;
        }

        Game game = MWAPI.getGameHandler().findById(id);

        if (game == null)
        {
            sender.sendMessage(ChatColor.RED + "Game with id " + args[1] + " not found.");
            return;
        }

        game.end(null);

        sender.sendMessage(ChatColor.GOLD + "Successfully ended game " + id + ".");
    }

    private void handleLeave(String[] args)
    {
        int id;
        try
        {
            id = Integer.parseInt(args[1]);
        }
        catch (Exception e)
        {
            sender.sendMessage(ChatColor.RED + "Game with id " + args[1] + " not found.");
            return;
        }

        Game game = MWAPI.getGameHandler().findById(id);

        if (game == null)
        {
            sender.sendMessage(ChatColor.RED + "Game with id " + args[1] + " not found.");
            return;
        }

        User user = MWAPI.getUserHandler().findById(((Player) sender).getUniqueId());

        if (game.isPlaying(user))
        {
            boolean success = game.leave(user);

            if (success)
            {
                sender.sendMessage(ChatColor.GOLD + "You left game " + ChatColor.AQUA + id + ChatColor.GOLD + ".");
            }
            else
            {
                sender.sendMessage(ChatColor.RED + "Could not leave game " + id + "!");
            }
        }
        else
        {
            sender.sendMessage(ChatColor.RED + "You are not part of game " + id + "!");
        }
    }

    private void handleJoin(String[] args)
    {
        int id;
        try
        {
            id = Integer.parseInt(args[1]);
        }
        catch (Exception e)
        {
            sender.sendMessage(ChatColor.RED + "Game with id " + args[1] + " not found.");
            return;
        }

        Game game = MWAPI.getGameHandler().findById(id);

        if (game == null)
        {
            sender.sendMessage(ChatColor.RED + "Game with id " + args[1] + " not found.");
            return;
        }

        User user = MWAPI.getUserHandler().findById(((Player) sender).getUniqueId());

        if (game.getState() == GameState.LOBBY)
        {
            boolean success = game.join(user);

            if (success)
            {
                sender.sendMessage(
                        ChatColor.GOLD + "Successfully joined game " + ChatColor.AQUA + id + ChatColor.GOLD + ".");
            }
            else
            {
                sender.sendMessage(ChatColor.RED + "Failed to join game " + id + "!");
            }

            return;
        }

        sender.sendMessage(ChatColor.RED + "Game with id " + args[1] + " is not joinable anymore.");
    }

    private void handleSpectate(String[] args)
    {
        int id;
        try
        {
            id = Integer.parseInt(args[1]);
        }
        catch (Exception e)
        {
            sender.sendMessage(ChatColor.RED + "Game with id " + args[1] + " not found.");
            return;
        }

        Game game = MWAPI.getGameHandler().findById(id);

        if (game == null)
        {
            sender.sendMessage(ChatColor.RED + "Game with id " + args[1] + " not found.");
            return;
        }

        User user = MWAPI.getUserHandler().findById(((Player) sender).getUniqueId());

        boolean success = game.spectate(user);

        if (success)
        {
            sender.sendMessage(
                    ChatColor.GOLD + "You are now spectating game " + ChatColor.AQUA + id + ChatColor.GOLD + ".");
        }
        else
        {
            sender.sendMessage(ChatColor.RED + "Game with id " + args[1] + " is currently not spectatable.");
        }
    }

    private void handleInfo(String[] args)
    {
        int id;
        try
        {
            id = Integer.parseInt(args[1]);
        }
        catch (Exception e)
        {
            sender.sendMessage(ChatColor.RED + "Game with id " + args[1] + " not found.");
            return;
        }

        Game game = MWAPI.getGameHandler().findById(id);

        if (game == null)
        {
            game = MWAPI.getOfflineGameHandler().findById(id);

            if (game == null)
            {
                sender.sendMessage(ChatColor.RED + "Game with id " + args[1] + " not found.");
                return;
            }
        }

        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&m--------------------- &r&6[ &aGame " +
                                                                       game.getId() + "&6 ]&6&m---------------------"));
        sender.sendMessage(ChatColor.GOLD + "Status: " + ChatColor.AQUA + game.getState());
        sender.sendMessage(ChatColor.GOLD + "Map: " + ChatColor.AQUA + game.getMap().getName());
        sender.sendMessage(ChatColor.GOLD + "Started at: " +
                           (game.getStartedAt() == null ? (ChatColor.RED + "N/A") : (ChatColor.AQUA +
                                                                                     game.getStartedAt().toString())));
        sender.sendMessage(ChatColor.GOLD + "Ended at: " +
                           (game.getEndedAt() == null ? (ChatColor.RED + "N/A") : (ChatColor.AQUA +
                                                                                   game.getEndedAt().toString())));
        sender.sendMessage(ChatColor.GOLD + "Team won: " +
                           (game.getWinner() == null ? (ChatColor.RED + "N/A") : (ChatColor.AQUA +
                                                                                  game.getWinner().name())));
    }

    private void handleCreate(String[] args)
    {
        Map map = MWAPI.getMapHandler().findByName(args[1]);

        if (map == null)
        {
            sender.sendMessage(ChatColor.RED + "Map with name " + args[1] + " not found.");
            return;
        }

        Game game = MWAPI.getGameHandler().createGame(map);

        if (game == null)
        {
            sender.sendMessage(ChatColor.RED + "Failed to create game with map " + args[1] + ".");
            return;
        }

        sender.sendMessage(
                ChatColor.GOLD + "Game with id " + ChatColor.AQUA + game.getId() + ChatColor.GOLD + " created.");
    }
}
