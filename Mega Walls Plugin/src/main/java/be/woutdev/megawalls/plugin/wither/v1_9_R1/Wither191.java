package be.woutdev.megawalls.plugin.wither.v1_9_R1;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.model.Team;
import be.woutdev.megawalls.plugin.model.BasicWither;
import be.woutdev.megawalls.plugin.wither.NMSWither;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_9_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R1.CraftWorld;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Wout on 8/06/2016.
 */
public class Wither191 extends EntityWither implements NMSWither
{
    private final Team team;
    private final Location location;

    public Wither191(World world)
    {
        super(world);

        team = null;
        location = null;
    }

    public Wither191(Team team, Location location)
    {
        super(((CraftWorld) location.getWorld()).getHandle());

        this.team = team;
        this.location = location;
    }

    @Override
    protected void M()
    {

    }

    @Override
    public boolean damageEntity(DamageSource damagesource, float f)
    {
        boolean defaultCheck = super.damageEntity(damagesource, f);

        int health = ((Double) Math.floor(getHealth())).intValue();

        if (!defaultCheck && !isInvulnerable(damagesource) && (health - f) > 0.1f)
        {
            return false;
        }

        if (health - f < 0.1f)
        {
            remove();

            String name = ChatColor.valueOf(team.getTeamColor().name()) + team.getTeamColor().name().substring(0, 1) +
                          team.getTeamColor().name().toLowerCase().substring(1);

            team.getGame()
                .sendMessage(ChatColor.GOLD + "The wither of team " + ChatColor.AQUA + name + ChatColor.GOLD +
                             " died! Players of that team will no longer respawn!");

            team.getPlayers()
                .forEach(p -> p.getPlayer().sendMessage(ChatColor.RED + "Your wither died! You will longer respawn."));

            ((BasicWither) team.getWither()).setDead(true);
            ((BasicWither) team.getWither()).setHealth(0);

            return true;
        }

        ((BasicWither) team.getWither()).setHealth(health - f);

        return true;
    }

    @Override
    public void move(double d0, double d1, double d2)
    {

    }

    @Override
    public void register()
    {
        ((Map) getField("c", EntityTypes.class, null)).put("Wither", this.getClass());
        ((Map) getField("d", EntityTypes.class, null)).put(this.getClass(), "Wither");
        ((Map) getField("e", EntityTypes.class, null)).put(64, this.getClass());
        ((Map) getField("f", EntityTypes.class, null)).put(this.getClass(), 64);
        ((Map) getField("g", EntityTypes.class, null)).put("Wither", 64);
    }

    @Override
    public void spawn()
    {
        String name =
                ChatColor.valueOf(team.getTeamColor().name()) + team.getTeamColor().name().substring(0, 1) +
                team.getTeamColor().name().toLowerCase().substring(
                        1) +
                " Wither";

        Set targetB = (Set) getField("b", PathfinderGoalSelector.class, targetSelector);
        Set targetC = (Set) getField("c", PathfinderGoalSelector.class, targetSelector);
        targetB.clear();
        targetC.clear();

        ((BossBattleServer) getField("bE", EntityWither.class, this)).setVisible(false);

        setCustomNameVisible(true);
        setCustomName(name);

        getAttributeInstance(GenericAttributes.maxHealth).setValue(team.getWither().getHealth());
        setHealth((float) team.getWither().getHealth());

        setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());

        Bukkit.getServer().getScheduler().runTask(MWAPI.getPlugin(), () -> getWorld().addEntity(this));

        setPositionRotation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
    }

    @Override
    public void remove()
    {
        die();
    }

    @Override
    public double getWitherHealth()
    {
        return getHealth();
    }

    @Override
    public String getWitherName()
    {
        return getCustomName();
    }

    @Override
    public UUID getEntityId()
    {
        return super.getUniqueID();
    }

    @Override
    public String getWorldName()
    {
        return getWorld().getWorld().getName();
    }

    private Object getField(String name, Class clazz, Object instance)
    {
        try
        {
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);

            if (Modifier.isFinal(field.getModifiers()))
            {
                Field modifiersField = Field.class.getDeclaredField("modifiers");
                modifiersField.setAccessible(true);
                modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            }

            return field.get(instance);
        }
        catch (NoSuchFieldException | IllegalAccessException e)
        {
            return null;
        }
    }
}
