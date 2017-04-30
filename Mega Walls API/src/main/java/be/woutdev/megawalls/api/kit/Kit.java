package be.woutdev.megawalls.api.kit;

import org.bukkit.potion.PotionEffect;

import java.util.Set;

/**
 * Created by Wout on 30/04/2017.
 */
public interface Kit
{
    KitItem getHelmet();

    KitItem getChestPlate();

    KitItem getPants();

    KitItem getBoots();

    KitItem getItemAt(int index);

    String getName();

    Set<PotionEffect> getEffects();
}
