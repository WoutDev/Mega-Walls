package be.woutdev.megawalls.plugin.listener;

import be.woutdev.megawalls.api.MWAPI;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * Created by Wout on 30/04/2017.
 */
public class CreatureSpawnListener implements Listener
{
    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent e)
    {
        if (e.getEntityType() != EntityType.PLAYER && e.getEntityType() != EntityType.WITHER)
        {
            if (MWAPI.getConfig().stopEntitySpawn())
            {
                e.setCancelled(true);
            }
        }
    }
}
