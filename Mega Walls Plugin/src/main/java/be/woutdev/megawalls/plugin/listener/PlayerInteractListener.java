package be.woutdev.megawalls.plugin.listener;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.enums.GameState;
import be.woutdev.megawalls.api.model.Game;
import be.woutdev.megawalls.api.model.User;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by Wout on 21/08/2016.
 */
public class PlayerInteractListener implements Listener
{
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e)
    {
        User user = MWAPI.getUserHandler().findById(e.getPlayer().getUniqueId());

        if (user.getGame() != null)
        {
            Game game = user.getGame();

            if (game.getState() == GameState.LOBBY && game.isPlaying(user))
            {
                e.setCancelled(true);
                e.getPlayer().updateInventory();

                if (e.getPlayer().getItemInHand().getType().equals(Material.CHEST))
                {
                    e.getPlayer().sendMessage(ChatColor.RED + "Coming Soon.");
                }
                else if (e.getPlayer().getItemInHand().getType().equals(Material.WOOL))
                {
                    e.getPlayer().sendMessage(ChatColor.RED + "Coming Soon.");
                }
                else if (e.getPlayer().getItemInHand().getType().equals(Material.BED))
                {
                    boolean success = game.leave(user);

                    if (success)
                    {
                        user.getPlayer().sendMessage(ChatColor.GOLD + "Successfully left game " + game.getId() + ".");
                    }
                    else
                    {
                        user.getPlayer().sendMessage(ChatColor.RED + "Error while leaving game " + game.getId() + ".");
                    }
                }
            }
        }
    }
}
