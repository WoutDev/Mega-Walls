package be.woutdev.megawalls.plugin.listener;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.enums.GameState;
import be.woutdev.megawalls.api.model.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

/**
 * Created by Wout on 21/08/2016.
 */
public class PlayerDropListener implements Listener
{
    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent e)
    {
        User user = MWAPI.getUserHandler().findById(e.getPlayer().getUniqueId());

        if (user.getGame() != null && user.getGame().getState() == GameState.LOBBY)
        {
            e.setCancelled(true);
            user.getPlayer().updateInventory();
        }
    }
}
