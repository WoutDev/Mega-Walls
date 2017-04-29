package be.woutdev.megawalls.api.handler;

import be.woutdev.megawalls.api.model.User;

import java.util.UUID;

/**
 * Created by Wout on 20/08/2016.
 */
public interface OfflineUserHandler extends OfflineHandler<User>
{
    User findById(UUID uuid);
}
