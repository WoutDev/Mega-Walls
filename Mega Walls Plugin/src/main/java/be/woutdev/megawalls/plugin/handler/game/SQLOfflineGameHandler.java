package be.woutdev.megawalls.plugin.handler.game;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.handler.OfflineGameHandler;
import be.woutdev.megawalls.api.model.Game;
import be.woutdev.megawalls.plugin.data.ebean.MapScalarTypeConverter;
import be.woutdev.megawalls.plugin.model.BasicGame;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebeaninternal.server.core.DefaultServer;
import com.avaje.ebeaninternal.server.ddl.DdlGenerator;

import java.util.List;

/**
 * Created by Wout on 20/08/2016.
 */
public class SQLOfflineGameHandler implements OfflineGameHandler
{
    private final EbeanServer server;

    public SQLOfflineGameHandler()
    {
        ServerConfig config = new ServerConfig();
        config.setDataSourceConfig(MWAPI.getConfig().getDataSourceConfig());
        config.addClass(BasicGame.class);
        config.addClass(MapScalarTypeConverter.class);

        server = EbeanServerFactory.create(config);

        try
        {
            server.find(BasicGame.class).findRowCount();
        }
        catch (Exception e)
        {
            DdlGenerator gen = ((DefaultServer) server).getDdlGenerator();
            gen.runScript(true, gen.generateCreateDdl());
        }
    }

    @Override
    public Game insert(Game object)
    {
        server.insert(object);

        return object;
    }

    @Override
    public Game update(Game object)
    {
        server.update(object);

        return object;
    }

    @Override
    public void delete(Game object)
    {
        server.delete(object);
    }

    @Override
    public List<Game> findAll()
    {
        return server.find(Game.class).findList();
    }

    @Override
    public Game findById(int id)
    {
        return server.find(Game.class).where().eq("id", id).findUnique();
    }
}
