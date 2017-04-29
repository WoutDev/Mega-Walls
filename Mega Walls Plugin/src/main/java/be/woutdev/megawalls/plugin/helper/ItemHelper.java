package be.woutdev.megawalls.plugin.helper;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Wout on 21/08/2016.
 */
public class ItemHelper
{
    public static ItemStack getKitSelector()
    {
        ItemStack stack = new ItemStack(Material.CHEST, 1);
        ItemMeta meta = stack.getItemMeta();

        meta.setDisplayName(ChatColor.GOLD + "Select Kit " + ChatColor.GRAY + "(Right click)");
        meta.setLore(null);

        stack.setItemMeta(meta);

        return stack;
    }

    public static ItemStack getTeamSelector()
    {
        ItemStack stack = new ItemStack(Material.WOOL, 1);
        ItemMeta meta = stack.getItemMeta();

        meta.setDisplayName(ChatColor.GOLD + "Select Team " + ChatColor.GRAY + "(Right click)");
        meta.setLore(null);

        stack.setItemMeta(meta);

        return stack;
    }

    public static ItemStack getLeaveItem()
    {
        ItemStack stack = new ItemStack(Material.BED, 1);
        ItemMeta meta = stack.getItemMeta();

        meta.setDisplayName(ChatColor.RED + "Leave Game " + ChatColor.GRAY + "(Right click)");
        meta.setLore(null);

        stack.setItemMeta(meta);

        return stack;
    }
}
