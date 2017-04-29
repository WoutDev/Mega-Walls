package be.woutdev.megawalls.plugin.data.ebean;

import com.avaje.ebean.config.ScalarTypeConverter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * Created by Wout on 20/08/2016.
 */
public class LocationScalarTypeConverter implements ScalarTypeConverter<Location, String>
{
    @Override
    public Location getNullValue()
    {
        return null;
    }

    @Override
    public Location wrapValue(String s)
    {
        String[] array = s.split(":");
        return new Location(Bukkit.getWorld(array[0]), Double.parseDouble(array[1]), Double.parseDouble(array[2]),
                            Double.parseDouble(array[3]));
    }

    @Override
    public String unwrapValue(Location location)
    {
        return location.getWorld().getName() + ":" + location.getX() + ":" + location.getY() + ":" + location.getZ();
    }
}
