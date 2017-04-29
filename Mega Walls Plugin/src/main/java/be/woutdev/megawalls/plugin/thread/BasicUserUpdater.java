package be.woutdev.megawalls.plugin.thread;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.handler.OfflineUserHandler;
import be.woutdev.megawalls.api.handler.UserHandler;
import be.woutdev.megawalls.api.model.User;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Wout on 20/08/2016.
 */
public class BasicUserUpdater extends BukkitRunnable
{
    private final UserHandler userHandler;
    private final OfflineUserHandler offlineUserHandler;

    public BasicUserUpdater()
    {
        this.userHandler = MWAPI.getUserHandler();
        this.offlineUserHandler = MWAPI.getOfflineUserHandler();
    }

    @Override
    public void run()
    {
        for (User user : userHandler.findAll())
        {
            offlineUserHandler.update(user);
        }
    }
}
