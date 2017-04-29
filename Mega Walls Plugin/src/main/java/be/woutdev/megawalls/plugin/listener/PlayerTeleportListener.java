package be.woutdev.megawalls.plugin.listener;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.model.User;
import be.woutdev.megawalls.plugin.handler.BasicTeleportHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * Created by Wout on 22/08/2016.
 */
public class PlayerTeleportListener implements Listener
{
    private final BasicTeleportHandler handler;

    public PlayerTeleportListener()
    {
        this.handler = (BasicTeleportHandler) MWAPI.getTeleportHandler();
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent e)
    {
        User user = MWAPI.getUserHandler().findById(e.getPlayer().getUniqueId());

        if (user.getGame() != null && (user.getGame().isPlaying(user) || user.getGame().isSpectating(user)))
        {
            if (handler.getAllowedTeleports().containsKey(user) &&
                handler.getAllowedTeleports().get(user).equals(e.getTo()))
            {
                handler.getAllowedTeleports().remove(user);
                return;
            }

            user.getGame().leave(user);
            user.getPlayer().teleport(e.getTo());
        }
    }
}
