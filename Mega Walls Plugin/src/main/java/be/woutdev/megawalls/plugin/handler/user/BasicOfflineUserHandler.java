package be.woutdev.megawalls.plugin.handler.user;

import be.woutdev.megawalls.api.handler.OfflineUserHandler;
import be.woutdev.megawalls.api.model.User;

import java.util.List;
import java.util.UUID;

/**
 * Created by Wout on 20/08/2016.
 */
public class BasicOfflineUserHandler implements OfflineUserHandler
{
    @Override
    public User insert(User object)
    {
        return null;
    }

    @Override
    public User update(User object)
    {
        return null;
    }

    @Override
    public void delete(User object)
    {

    }

    @Override
    public List<User> findAll()
    {
        return null;
    }

    @Override
    public User findById(UUID uuid)
    {
        return null;
    }
}
