package be.woutdev.megawalls.api.helper;

import org.bukkit.entity.Player;

/**
 * Created by Wout on 19/08/2016.
 */
public interface PermissionHelper
{
    boolean hasPermission(Player p, String permission);
}
