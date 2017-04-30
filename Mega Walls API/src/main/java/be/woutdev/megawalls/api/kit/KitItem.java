package be.woutdev.megawalls.api.kit;

import org.bukkit.inventory.ItemStack;

/**
 * Created by Wout on 30/04/2017.
 */
public interface KitItem
{
    ItemStack getStack();

    boolean isDroppable();

    boolean isUnbreakable();
}
