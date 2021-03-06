package be.woutdev.megawalls.api.framework;

import be.woutdev.megawalls.api.config.Config;
import be.woutdev.megawalls.api.handler.*;
import be.woutdev.megawalls.api.helper.PermissionHelper;
import be.woutdev.megawalls.api.kit.KitBuilder;
import be.woutdev.megawalls.api.kit.KitHandler;
import be.woutdev.megawalls.api.kit.KitItemBuilder;
import org.bukkit.plugin.Plugin;

/**
 * Created by Wout on 19/08/2016.
 */
public interface Framework
{
    MapHandler getMapHandler();

    GameHandler getGameHandler();

    OfflineGameHandler getOfflineGameHandler();

    UserHandler getUserHandler();

    OfflineUserHandler getOfflineUserHandler();

    TeamHandler getTeamHandler();

    OfflineTeamHandler getOfflineTeamHandler();

    TeleportHandler getTeleportHandler();

    Config getConfig();

    Plugin getPlugin();

    PermissionHelper getPermissionHelper();

    KitBuilder getKitBuilder(String name);

    KitHandler getKitHandler();

    KitItemBuilder getKitItemBuilder();
}
