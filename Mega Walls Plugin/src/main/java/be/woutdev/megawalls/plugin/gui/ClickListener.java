package be.woutdev.megawalls.plugin.gui;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Wout on 30/04/2017.
 */
public interface ClickListener
{
    void onClick(ItemStack stack, int index, Inventory inv);
}
