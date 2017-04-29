package be.woutdev.megawalls.plugin;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.plugin.command.MainCommand;
import be.woutdev.megawalls.plugin.config.MainConfig;
import be.woutdev.megawalls.plugin.framework.MegaWallsFramework;
import be.woutdev.megawalls.plugin.handler.user.SQLOfflineUserHandler;
import be.woutdev.megawalls.plugin.listener.*;
import be.woutdev.megawalls.plugin.thread.BasicGameUpdater;
import be.woutdev.megawalls.plugin.thread.BasicUserUpdater;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Wout on 19/08/2016.
 */
public class MegaWalls extends JavaPlugin
{
    private MainConfig mainConfig;

    @Override
    public FileConfiguration getConfig()
    {
        return mainConfig.getFileConfiguration();
    }

    @Override
    public void onLoad()
    {
        mainConfig = new MainConfig(this, "config.yml");
        mainConfig.load();
    }

    @Override
    public void onDisable()
    {
        MWAPI.getGameHandler().findActive().forEach(game -> game.end(null));
    }

    @Override
    public void onEnable()
    {
        MWAPI.setFramework(new MegaWallsFramework(this));

        getCommand("mw").setExecutor(new MainCommand());

        getServer().getPluginManager().registerEvents(new PlayerLoginListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDropListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerTeleportListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);

        if (MWAPI.getOfflineUserHandler() instanceof SQLOfflineUserHandler)
        {
            new BasicUserUpdater().runTaskTimerAsynchronously(this, 100L, 100L);
            new BasicGameUpdater().runTaskTimerAsynchronously(this, 100L, 100L);
        }
    }

    public MainConfig getMainConfig()
    {
        return mainConfig;
    }
}
