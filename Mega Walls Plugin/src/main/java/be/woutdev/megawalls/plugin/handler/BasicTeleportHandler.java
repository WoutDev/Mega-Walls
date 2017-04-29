package be.woutdev.megawalls.plugin.handler;

import be.woutdev.megawalls.api.handler.TeleportHandler;
import be.woutdev.megawalls.api.model.User;
import org.bukkit.Location;

import java.util.HashMap;

/**
 * Created by Wout on 22/08/2016.
 */
public class BasicTeleportHandler implements TeleportHandler
{
    private final HashMap<User, Location> allowedTeleports;

    public BasicTeleportHandler()
    {
        this.allowedTeleports = new HashMap<>();
    }

    @Override
    public void teleport(User user, Location to)
    {
        allowedTeleports.put(user, to);

        user.getPlayer().teleport(to);
    }

    public HashMap<User, Location> getAllowedTeleports()
    {
        return allowedTeleports;
    }
}
