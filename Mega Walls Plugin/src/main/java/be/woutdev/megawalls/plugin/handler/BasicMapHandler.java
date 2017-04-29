package be.woutdev.megawalls.plugin.handler;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.enums.TeamColor;
import be.woutdev.megawalls.api.handler.MapHandler;
import be.woutdev.megawalls.api.model.Map;
import be.woutdev.megawalls.plugin.data.gson.EnumMapInstanceCreator;
import be.woutdev.megawalls.plugin.data.gson.LocationAdapter;
import be.woutdev.megawalls.plugin.model.BasicMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wout on 19/08/2016.
 */
public class BasicMapHandler implements MapHandler
{
    private final Set<Map> maps;
    private final Gson gson;
    private final Path dir;

    public BasicMapHandler()
    {
        this.maps = new HashSet<>();
        this.gson = new GsonBuilder().registerTypeAdapter(Location.class, new LocationAdapter()).registerTypeAdapter(
                new TypeToken<EnumMap<TeamColor, Location>>() {}.getType(),
                new EnumMapInstanceCreator<TeamColor, Location>(TeamColor.class)).create();
        this.dir = Paths.get(
                Bukkit.getServer().getPluginManager().getPlugin("MegaWalls").getDataFolder().getAbsolutePath(), "maps");

        if (!Files.exists(dir))
        {
            try
            {
                Files.createDirectory(dir);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        try
        {
            Files.walkFileTree(dir, new SimpleFileVisitor<Path>()
            {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
                {
                    if (file.toString().toLowerCase().endsWith(".json"))
                    {
                        FileReader in = new FileReader(file.toFile());

                        maps.add(gson.fromJson(in, BasicMap.class));

                        in.close();
                    }

                    return FileVisitResult.CONTINUE;
                }
            });
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean create(String name)
    {
        if (exists(name) || name.equalsIgnoreCase("default"))
        {
            return false;
        }

        BasicMap map = new BasicMap(name);

        try
        {
            FileWriter out = new FileWriter(Paths.get(dir.toAbsolutePath().toString(), name + ".json").toFile());

            gson.toJson(map, BasicMap.class, out);

            out.close();

            maps.add(map);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean delete(String name)
    {
        Map map = findByName(name);

        if (map == null)
        {
            return false;
        }

        if (MWAPI.getGameHandler().findActive().stream().anyMatch(g -> g.getMap().equals(map)))
        {
            return false;
        }

        try
        {
            Files.delete(Paths.get(dir.toAbsolutePath().toString(), name + ".json"));

            maps.remove(map);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public Map findByName(String name)
    {
        if (!exists(name))
        {
            return null;
        }

        return maps.stream().filter(m -> m.getName().equalsIgnoreCase(name)).findAny().orElse(null);
    }

    @Override
    public boolean exists(String name)
    {
        Path file = Paths.get(dir.toAbsolutePath().toString(), name + ".json");

        return Files.exists(file);
    }

    @Override
    public Set<Map> findAll()
    {
        return maps;
    }

    public void update(BasicMap map)
    {
        try
        {
            FileWriter out = new FileWriter(
                    Paths.get(dir.toAbsolutePath().toString(), map.getName() + ".json").toFile());
            gson.toJson(map, BasicMap.class, out);
            out.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
