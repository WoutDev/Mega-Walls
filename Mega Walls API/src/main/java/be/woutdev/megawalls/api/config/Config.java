package be.woutdev.megawalls.api.config;

import be.woutdev.megawalls.api.model.Map;
import com.avaje.ebean.config.DataSourceConfig;

/**
 * Created by Wout on 20/08/2016.
 */
public interface Config
{
    DataSourceConfig getDataSourceConfig();

    boolean isTeamSelectionEnabled();

    boolean stopEntitySpawn();

    boolean isDefaultKitsEnabled();

    int getMinTeamSize(Map map);

    int getMaxTeamSize(Map map);

    boolean shouldAutoStart(Map map);

    int getLobbyCountdown(Map map);

    int getPrepareCountdown(Map map);

    int getLateCountdown(Map map);

    int getDrawCountdown(Map map);

    int getDefaultWitherHealth(Map map);
}
