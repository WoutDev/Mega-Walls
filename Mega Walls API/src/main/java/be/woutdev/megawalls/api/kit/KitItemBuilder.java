package be.woutdev.megawalls.api.kit;

import org.bukkit.inventory.ItemStack;

/**
 * Created by Wout on 30/04/2017.
 */
public interface KitItemBuilder
{
    KitItemBuilder stack(ItemStack stack);

    KitItemBuilder drop(boolean drop);

    KitItemBuilder unbreakable(boolean unbreakable);

    KitItem create();
}
