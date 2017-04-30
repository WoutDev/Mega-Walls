package be.woutdev.megawalls.api.kit;

import org.bukkit.potion.PotionEffect;

/**
 * Created by Wout on 30/04/2017.
 */
public interface KitBuilder
{
    KitBuilder setHelmet(KitItem item);

    KitBuilder setChestPlate(KitItem item);

    KitBuilder setPants(KitItem item);

    KitBuilder setBoots(KitItem item);

    KitBuilder setItemAt(int index, KitItem item);

    KitBuilder addEffect(PotionEffect effect);

    Kit create();
}
