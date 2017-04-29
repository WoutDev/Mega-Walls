package be.woutdev.megawalls.plugin.helper;

import be.woutdev.megawalls.api.helper.PermissionHelper;
import org.bukkit.entity.Player;

/**
 * Created by Wout on 19/08/2016.
 */
public class BasicPermissionHelper implements PermissionHelper
{
    @Override
    public boolean hasPermission(Player p, String permission)
    {
        return p.isOp() || p.hasPermission("mw.*") || p.hasPermission("mw.admin") ||
               p.hasPermission("mw." + permission) || p.hasPermission("megawalls.*") ||
               p.hasPermission("megawalls.admin") || p.hasPermission("megawalls." + permission);
    }
}
