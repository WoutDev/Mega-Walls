package be.woutdev.megawalls.api;

import be.woutdev.megawalls.api.config.Config;
import be.woutdev.megawalls.api.framework.Framework;
import be.woutdev.megawalls.api.handler.*;
import be.woutdev.megawalls.api.helper.PermissionHelper;
import be.woutdev.megawalls.api.kit.KitBuilder;
import be.woutdev.megawalls.api.kit.KitItemBuilder;
import org.bukkit.plugin.Plugin;

/**
 * Created by Wout on 19/08/2016.
 */
public final class MWAPI
{
    private static Framework framework;

    public static PermissionHelper getPermissionHelper()
    {
        return framework.getPermissionHelper();
    }

    public static MapHandler getMapHandler()
    {
        return framework.getMapHandler();
    }

    public static GameHandler getGameHandler()
    {
        return framework.getGameHandler();
    }

    public static OfflineGameHandler getOfflineGameHandler()
    {
        return framework.getOfflineGameHandler();
    }

    public static UserHandler getUserHandler()
    {
        return framework.getUserHandler();
    }

    public static OfflineUserHandler getOfflineUserHandler()
    {
        return framework.getOfflineUserHandler();
    }

    public static TeleportHandler getTeleportHandler()
    {
        return framework.getTeleportHandler();
    }

    public static TeamHandler getTeamHandler()
    {
        return framework.getTeamHandler();
    }

    public static OfflineTeamHandler getOfflineTeamHandler()
    {
        return framework.getOfflineTeamHandler();
    }

    public static Config getConfig()
    {
        return framework.getConfig();
    }

    public static Plugin getPlugin()
    {
        return framework.getPlugin();
    }

    public static Framework getFramework()
    {
        return framework;
    }

    public static KitBuilder getKitBuilder(String name) { return framework.getKitBuilder(name); }

    public static KitItemBuilder getKitItemBuilder() { return framework.getKitItemBuilder(); }

    public static void setFramework(Framework framework)
    {
        if (MWAPI.framework != null)
        {
            throw new RuntimeException("Cannot set framework twice.");
        }

        MWAPI.framework = framework;
    }
}
