package be.woutdev.megawalls.plugin.scoreboard.objects;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by Wout on 12/06/2016.
 */
public class FakeOfflinePlayer implements OfflinePlayer
{
    private final String name;

    public FakeOfflinePlayer(String name)
    {
        this.name = name;
    }

    @Override
    public boolean isOnline()
    {
        return false;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public UUID getUniqueId()
    {
        return UUID.nameUUIDFromBytes(name.getBytes(Charsets.UTF_8));
    }

    @Override
    public boolean isBanned()
    {
        return false;
    }

    @Override
    public void setBanned(boolean b)
    {

    }

    @Override
    public boolean isWhitelisted()
    {
        return false;
    }

    @Override
    public void setWhitelisted(boolean b)
    {

    }

    @Override
    public Player getPlayer()
    {
        return null;
    }

    @Override
    public long getFirstPlayed()
    {
        return System.currentTimeMillis();
    }

    @Override
    public long getLastPlayed()
    {
        return System.currentTimeMillis();
    }

    @Override
    public boolean hasPlayedBefore()
    {
        return false;
    }

    @Override
    public Location getBedSpawnLocation()
    {
        return null;
    }

    @Override
    public Map<String, Object> serialize()
    {
        Map<String, Object> result = Maps.newLinkedHashMap();

        result.put("UUID", getUniqueId().toString());
        result.put("name", name);

        return result;
    }

    @Override
    public boolean isOp()
    {
        return false;
    }

    @Override
    public void setOp(boolean b)
    {

    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(name);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof FakeOfflinePlayer))
        {
            return false;
        }

        FakeOfflinePlayer other = (FakeOfflinePlayer) obj;
        return Objects.equals(name, other.name);
    }

    @Override
    public String toString()
    {
        return "{name: " + name + "}";
    }
}
