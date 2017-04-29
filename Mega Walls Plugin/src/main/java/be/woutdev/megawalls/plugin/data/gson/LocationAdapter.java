package be.woutdev.megawalls.plugin.data.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.IOException;

/**
 * Created by Wout on 19/08/2016.
 */
public class LocationAdapter extends TypeAdapter<Location>
{
    @Override
    public void write(JsonWriter out, Location location) throws IOException
    {
        out.beginObject();
        out.name("x").value(location == null ? 30000001 : location.getX());
        out.name("y").value(location == null ? 30000001 : location.getY());
        out.name("z").value(location == null ? 30000001 : location.getZ());
        out.name("pitch").value(location == null ? 30000001 : location.getPitch());
        out.name("yaw").value(location == null ? 30000001 : location.getYaw());
        out.name("world").value((location == null || location.getWorld() == null) ? "" : location.getWorld().getName());
        out.endObject();
    }

    @Override
    public Location read(JsonReader in) throws IOException
    {
        in.beginObject();

        in.nextName();
        double x = in.nextDouble();

        in.nextName();
        double y = in.nextDouble();

        in.nextName();
        double z = in.nextDouble();

        in.nextName();
        float pitch = Float.valueOf(String.valueOf(in.nextDouble()));

        in.nextName();
        float yaw = Float.valueOf(String.valueOf(in.nextDouble()));

        in.nextName();
        World world = Bukkit.getWorld(in.nextString());

        in.endObject();

        return new Location(world, x, y, z, pitch, yaw);
    }
}
