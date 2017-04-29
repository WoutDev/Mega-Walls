package be.woutdev.megawalls.plugin.listener;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.enums.GameState;
import be.woutdev.megawalls.api.model.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Created by Wout on 21/08/2016.
 */
public class BlockPlaceListener implements Listener
{
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e)
    {
        User user = MWAPI.getUserHandler().findById(e.getPlayer().getUniqueId());

        if (user.getGame() != null)
        {
            if (user.getGame().getSpectators().contains(user) || user.getGame().getState().equals(GameState.LOBBY))
            {
                e.setCancelled(true);
                return;
            }

            // TODO handle other stuff like the castles and walls
        }
    }
}
