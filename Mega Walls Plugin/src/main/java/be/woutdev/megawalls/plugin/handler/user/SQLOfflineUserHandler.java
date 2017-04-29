package be.woutdev.megawalls.plugin.handler.user;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.handler.OfflineUserHandler;
import be.woutdev.megawalls.api.model.User;
import be.woutdev.megawalls.plugin.model.BasicUser;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.EbeanServerFactory;
import com.avaje.ebean.config.ServerConfig;
import com.avaje.ebeaninternal.server.core.DefaultServer;
import com.avaje.ebeaninternal.server.ddl.DdlGenerator;

import java.util.List;
import java.util.UUID;

/**
 * Created by Wout on 20/08/2016.
 */
public class SQLOfflineUserHandler implements OfflineUserHandler
{
    private final EbeanServer server;

    public SQLOfflineUserHandler()
    {
        ServerConfig config = new ServerConfig();
        config.setDataSourceConfig(MWAPI.getConfig().getDataSourceConfig());
        config.addClass(BasicUser.class);

        server = EbeanServerFactory.create(config);

        try
        {
            server.find(BasicUser.class).findRowCount();
        }
        catch (Exception e)
        {
            DdlGenerator gen = ((DefaultServer) server).getDdlGenerator();
            gen.runScript(true, gen.generateCreateDdl());
        }
    }

    @Override
    public User insert(User user)
    {
        server.insert(user);

        return user;
    }

    @Override
    public User update(User user)
    {
        server.update(user);

        return user;
    }

    @Override
    public void delete(User object)
    {
        server.delete(object);
    }

    @Override
    public List<User> findAll()
    {
        return server.find(User.class).findList();
    }

    public User findById(UUID id)
    {
        return server.find(User.class).where().eq("uniqueId", id.toString()).findUnique();
    }
}
