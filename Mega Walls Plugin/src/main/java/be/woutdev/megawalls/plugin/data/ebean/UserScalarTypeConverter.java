package be.woutdev.megawalls.plugin.data.ebean;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.model.User;
import com.avaje.ebean.config.ScalarTypeConverter;

import java.util.UUID;

/**
 * Created by Wout on 20/08/2016.
 */
public class UserScalarTypeConverter implements ScalarTypeConverter<User, UUID>
{
    @Override
    public User getNullValue()
    {
        return null;
    }

    @Override
    public User wrapValue(UUID scalarType)
    {
        User user = MWAPI.getUserHandler().findById(scalarType);

        if (user != null)
        {
            return user;
        }

        user = MWAPI.getOfflineUserHandler().findById(scalarType);

        return user;
    }

    @Override
    public UUID unwrapValue(User beanType)
    {
        return beanType.getUniqueId();
    }
}
