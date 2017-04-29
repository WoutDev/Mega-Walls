package be.woutdev.megawalls.plugin.handler.user;

import be.woutdev.megawalls.api.handler.UserHandler;
import be.woutdev.megawalls.api.model.User;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Wout on 20/08/2016.
 */
public class BasicUserHandler implements UserHandler
{
    private final Set<User> users;

    public BasicUserHandler()
    {
        users = new HashSet<>();
    }

    @Override
    public User findById(UUID uniqueId)
    {
        return users.stream().filter(u -> u.getUniqueId().equals(uniqueId)).findAny().orElse(null);
    }

    @Override
    public Set<User> findAll()
    {
        return users;
    }

    public void add(User user)
    {
        users.add(user);
    }

    public void remove(User user)
    {
        users.remove(user);
    }
}
