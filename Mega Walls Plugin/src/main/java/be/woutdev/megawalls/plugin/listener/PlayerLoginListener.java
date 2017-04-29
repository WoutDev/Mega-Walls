package be.woutdev.megawalls.plugin.listener;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.model.User;
import be.woutdev.megawalls.plugin.handler.user.BasicUserHandler;
import be.woutdev.megawalls.plugin.model.BasicUser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * Created by Wout on 20/08/2016.
 */
public class PlayerLoginListener implements Listener
{
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerLogin(PlayerLoginEvent e)
    {
        if (e.getResult() == PlayerLoginEvent.Result.ALLOWED)
        {
            User user = MWAPI.getOfflineUserHandler().findById(e.getPlayer().getUniqueId());

            if (user == null)
            {
                user = new BasicUser(e.getPlayer().getUniqueId());

                MWAPI.getOfflineUserHandler().insert(user);
            }

            ((BasicUserHandler) MWAPI.getUserHandler()).add(user);
        }
    }
}
