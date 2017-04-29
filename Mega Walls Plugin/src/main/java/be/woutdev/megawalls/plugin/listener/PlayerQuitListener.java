package be.woutdev.megawalls.plugin.listener;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.model.User;
import be.woutdev.megawalls.plugin.handler.user.BasicUserHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Wout on 20/08/2016.
 */
public class PlayerQuitListener implements Listener
{
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e)
    {
        BasicUserHandler handler = (BasicUserHandler) MWAPI.getUserHandler();
        User user = handler.findById(e.getPlayer().getUniqueId());

        if (user.getGame() != null && (user.getGame().isPlaying(user) || user.getGame().isSpectating(user)))
        {
            user.getGame().leave(user);
        }

        MWAPI.getOfflineUserHandler().update(user);

        handler.remove(user);
    }
}
