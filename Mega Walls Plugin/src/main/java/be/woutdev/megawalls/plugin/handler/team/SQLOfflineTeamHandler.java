package be.woutdev.megawalls.plugin.handler.team;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.handler.OfflineTeamHandler;
import be.woutdev.megawalls.api.model.Team;
import be.woutdev.megawalls.plugin.model.BasicTeam;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebeaninternal.server.core.DefaultServer;
import com.avaje.ebeaninternal.server.ddl.DdlGenerator;

import java.util.List;

/**
 * Created by Wout on 20/08/2016.
 */
public class SQLOfflineTeamHandler implements OfflineTeamHandler
{
    private final EbeanServer server;

    public SQLOfflineTeamHandler()
    {
        ServerConfig config = new ServerConfig();
        config.setDataSourceConfig(MWAPI.getConfig().getDataSourceConfig());
        config.addClass(BasicTeam.class);

        server = EbeanServerFactory.create(config);

        try
        {
            server.find(BasicTeam.class).findRowCount();
        }
        catch (Exception e)
        {
            DdlGenerator gen = ((DefaultServer) server).getDdlGenerator();
            gen.runScript(true, gen.generateCreateDdl());
        }
    }

    @Override
    public Team insert(Team object)
    {
        server.insert(object);

        return object;
    }

    @Override
    public Team update(Team object)
    {
        server.update(object);

        return object;
    }

    @Override
    public void delete(Team object)
    {
        server.delete(object);
    }

    @Override
    public List<Team> findAll()
    {
        return server.find(Team.class).findList();
    }
}
