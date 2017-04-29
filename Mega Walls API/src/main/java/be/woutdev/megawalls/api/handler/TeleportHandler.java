package be.woutdev.megawalls.api.handler;

import be.woutdev.megawalls.api.model.User;
import org.bukkit.Location;

/**
 * Created by Wout on 22/08/2016.
 */
public interface TeleportHandler
{
    void teleport(User user, Location to);
}
