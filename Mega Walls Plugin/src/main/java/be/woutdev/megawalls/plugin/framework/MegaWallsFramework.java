package be.woutdev.megawalls.plugin.framework;

import be.woutdev.megawalls.api.config.Config;
import be.woutdev.megawalls.api.framework.Framework;
import be.woutdev.megawalls.api.handler.*;
import be.woutdev.megawalls.api.helper.PermissionHelper;
import be.woutdev.megawalls.plugin.MegaWalls;
import be.woutdev.megawalls.plugin.handler.BasicMapHandler;
import be.woutdev.megawalls.plugin.handler.BasicTeleportHandler;
import be.woutdev.megawalls.plugin.handler.game.BasicGameHandler;
import be.woutdev.megawalls.plugin.handler.game.BasicOfflineGameHandler;
import be.woutdev.megawalls.plugin.handler.game.SQLOfflineGameHandler;
import be.woutdev.megawalls.plugin.handler.team.BasicOfflineTeamHandler;
import be.woutdev.megawalls.plugin.handler.team.BasicTeamHandler;
import be.woutdev.megawalls.plugin.handler.team.SQLOfflineTeamHandler;
import be.woutdev.megawalls.plugin.handler.user.BasicOfflineUserHandler;
import be.woutdev.megawalls.plugin.handler.user.BasicUserHandler;
import be.woutdev.megawalls.plugin.handler.user.SQLOfflineUserHandler;
import be.woutdev.megawalls.plugin.helper.BasicPermissionHelper;
import com.avaje.ebean.config.DataSourceConfig;
import org.bukkit.plugin.Plugin;

/**
 * Created by Wout on 19/08/2016.
 */
public class MegaWallsFramework implements Framework
{
    private final Plugin plugin;
    private final Config config;
    private final PermissionHelper permissionHelper;
    private final MapHandler mapHandler;
    private final GameHandler gameHandler;
    private final OfflineGameHandler offlineGameHandler;
    private final UserHandler userHandler;
    private final OfflineUserHandler offlineUserHandler;
    private final TeamHandler teamHandler;
    private final OfflineTeamHandler offlineTeamHandler;
    private final TeleportHandler teleportHandler;

    public MegaWallsFramework(Plugin plugin)
    {
        this.plugin = plugin;
        this.config = ((MegaWalls) plugin).getMainConfig();
        this.permissionHelper = new BasicPermissionHelper();
        this.mapHandler = new BasicMapHandler();
        this.gameHandler = new BasicGameHandler();
        this.userHandler = new BasicUserHandler();
        this.teamHandler = new BasicTeamHandler();
        this.teleportHandler = new BasicTeleportHandler();

        DataSourceConfig dataSourceConfig = config.getDataSourceConfig();

        if (dataSourceConfig == null)
        {
            this.offlineGameHandler = new BasicOfflineGameHandler();
            this.offlineUserHandler = new BasicOfflineUserHandler();
            this.offlineTeamHandler = new BasicOfflineTeamHandler();
        }
        else
        {
            this.offlineGameHandler = new SQLOfflineGameHandler();
            this.offlineUserHandler = new SQLOfflineUserHandler();
            this.offlineTeamHandler = new SQLOfflineTeamHandler();
        }
    }

    @Override
    public MapHandler getMapHandler()
    {
        return mapHandler;
    }

    @Override
    public GameHandler getGameHandler()
    {
        return gameHandler;
    }

    @Override
    public OfflineGameHandler getOfflineGameHandler()
    {
        return offlineGameHandler;
    }

    @Override
    public UserHandler getUserHandler()
    {
        return userHandler;
    }

    @Override
    public OfflineUserHandler getOfflineUserHandler()
    {
        return offlineUserHandler;
    }

    @Override
    public TeamHandler getTeamHandler()
    {
        return teamHandler;
    }

    @Override
    public OfflineTeamHandler getOfflineTeamHandler()
    {
        return offlineTeamHandler;
    }

    @Override
    public TeleportHandler getTeleportHandler()
    {
        return teleportHandler;
    }

    @Override
    public Config getConfig()
    {
        return config;
    }

    @Override
    public Plugin getPlugin()
    {
        return plugin;
    }

    @Override
    public PermissionHelper getPermissionHelper()
    {
        return permissionHelper;
    }
}
