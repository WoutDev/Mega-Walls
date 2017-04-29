package be.woutdev.megawalls.plugin.listener;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.model.User;
import be.woutdev.megawalls.plugin.model.BasicWither;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by Wout on 30/04/2017.
 */
public class EntityDamageByEntityListener implements Listener
{
    @EventHandler
    public void onEntityDamageByEntityListener(EntityDamageByEntityEvent e)
    {
        User user = null;

        if (e.getDamager() instanceof Player)
        {
            user = MWAPI.getUserHandler().findById(e.getDamager().getUniqueId());
        }
        else if (e.getDamager() instanceof Projectile)
        {
            Projectile p = (Projectile) e.getDamager();

            if (p.getShooter() instanceof Player)
            {
                user = MWAPI.getUserHandler().findById(((Player) p.getShooter()).getUniqueId());
            }
        }

        if (user == null || !user.isPlaying()) return;

        if (e.getEntity() instanceof Wither)
        {
            Wither wither = (Wither) e.getEntity();

            if (((BasicWither) user.getGame().getTeam(user).getWither()).getWither().getEntityId().equals(wither.getUniqueId()))
            {
                e.setCancelled(true);
            }
        }
        else if (e.getEntity() instanceof Player)
        {
            User target = MWAPI.getUserHandler().findById(e.getEntity().getUniqueId());

            if (target.getGame().getTeam(target).getTeamColor() == user.getGame().getTeam(user).getTeamColor())
            {
                e.setCancelled(true);
            }
        }
    }
}
