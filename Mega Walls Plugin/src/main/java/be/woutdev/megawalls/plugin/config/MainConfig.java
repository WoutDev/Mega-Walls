package be.woutdev.megawalls.plugin.config;

import be.woutdev.megawalls.api.config.Config;
import be.woutdev.megawalls.api.model.Map;
import com.avaje.ebean.config.DataSourceConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Wout on 19/08/2016.
 */
public class MainConfig implements Config
{
    private final Plugin plugin;
    private final String name;
    private FileConfiguration fileConfiguration;

    public MainConfig(Plugin plugin, String name)
    {
        this.plugin = plugin;
        this.name = name;
    }

    public void load()
    {
        Path mapPath = Paths.get(plugin.getDataFolder().getAbsolutePath());

        if (!Files.exists(mapPath))
        {
            try
            {
                Files.createDirectory(mapPath);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        Path configPath = Paths.get(mapPath.toAbsolutePath().toString(), name);

        if (!Files.exists(configPath))
        {
            try
            {
                Files.copy(plugin.getResource(name), configPath);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        fileConfiguration = YamlConfiguration.loadConfiguration(configPath.toFile());
    }

    public FileConfiguration getFileConfiguration()
    {
        return fileConfiguration;
    }

    @Override
    public DataSourceConfig getDataSourceConfig()
    {
        DataSourceConfig config = new DataSourceConfig();

        if (getFileConfiguration().getString("data.method").equalsIgnoreCase("sqlite"))
        {
            config.setDriver("org.sqlite.JDBC");
            config.setUrl("jdbc:sqlite:games.db");

            return config;
        }
        else if (getFileConfiguration().getString("data.method").equalsIgnoreCase("mysql"))
        {
            config.setDriver("com.mysql.jdbc.Driver");
            config.setUrl("jdbc:mysql://" + getFileConfiguration().getString("data.mysql.host") + ":" +
                          getFileConfiguration().getInt("data.mysql.port") + "/" +
                          getFileConfiguration().getString("data.mysql.database"));
            config.setUsername(getFileConfiguration().getString("data.mysql.user"));
            config.setPassword(getFileConfiguration().getString("data.mysql.password"));

            return config;
        }

        return null;
    }

    @Override
    public boolean isTeamSelectionEnabled()
    {
        return getFileConfiguration().getBoolean("settings.team-selection");
    }

    @Override
    public boolean stopEntitySpawn()
    {
        return getFileConfiguration().getBoolean("settings.stop-entity-spawn");
    }

    @Override
    public int getMinTeamSize(Map map)
    {
        return getFileConfiguration().getInt("settings.maps." + map.getName() + ".min-per-team",
                                             getFileConfiguration().getInt("settings.maps.default.min-per-team"));
    }

    @Override
    public int getMaxTeamSize(Map map)
    {
        return getFileConfiguration().getInt("settings.maps." + map.getName() + ".max-per-team",
                                             getFileConfiguration().getInt("settings.maps.default.max-per-team"));
    }

    @Override
    public boolean shouldAutoStart(Map map)
    {
        return getFileConfiguration().getBoolean("settings.maps." + map.getName() + ".autostart",
                                                 getFileConfiguration().getBoolean("settings.maps.default.autostart"));
    }

    @Override
    public int getLobbyCountdown(Map map)
    {
        return getFileConfiguration().getInt("settings.maps." + map.getName() + ".lobby-countdown",
                                             getFileConfiguration().getInt("settings.maps.lobby-countdown"));
    }

    @Override
    public int getPrepareCountdown(Map map)
    {
        return getFileConfiguration().getInt("settings.maps." + map.getName() + ".prepare-countdown",
                                             getFileConfiguration().getInt("settings.maps.default.prepare-countdown"));
    }

    @Override
    public int getLateCountdown(Map map)
    {
        return getFileConfiguration().getInt("settings.maps." + map.getName() + ".late-countdown",
                                             getFileConfiguration().getInt("settings.maps.default.late-countdown"));
    }

    @Override
    public int getDrawCountdown(Map map)
    {
        return getFileConfiguration().getInt("settings.maps." + map.getName() + ".draw-countdown",
                                             getFileConfiguration().getInt("settings.maps.default.draw-countdown"));
    }

    @Override
    public int getDefaultWitherHealth(Map map)
    {
        return getFileConfiguration().getInt("settings.maps." + map.getName() + ".wither-health",
                                             getFileConfiguration().getInt("settings.maps.default.wither-health"));
    }
}