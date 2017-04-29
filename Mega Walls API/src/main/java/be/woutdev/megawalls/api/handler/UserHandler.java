package be.woutdev.megawalls.api.handler;

import be.woutdev.megawalls.api.model.User;

import java.util.Set;
import java.util.UUID;

/**
 * Created by Wout on 20/08/2016.
 */
public interface UserHandler
{
    User findById(UUID uniqueId);

    Set<User> findAll();
}
