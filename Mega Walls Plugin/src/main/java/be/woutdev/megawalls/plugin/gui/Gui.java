package be.woutdev.megawalls.plugin.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Set;

/**
 * Created by Wout on 30/04/2017.
 */
public class Gui
{
    private final String name;
    private final String title;
    private final Set<GuiItem> items;

    public Gui(String name, String title, Set<GuiItem> items)
    {
        this.name = name;
        this.title = title;
        this.items = items;
    }

    public String getName()
    {
        return name;
    }

    public String getTitle()
    {
        return title;
    }

    public Set<GuiItem> getItems()
    {
        return items;
    }

    public GuiItem getItemAt(int index)
    {
        return items.stream().filter(i -> i.getIndex() == index).findAny().orElse(null);
    }

    public void open(Player p)
    {
        int size = 9;

        for (int i = 1; i <= 6; i++)
        {
            if (9 * i > items.size())
                size = 9 * i;
        }

        Inventory inv = Bukkit.createInventory(null, size, ChatColor.translateAlternateColorCodes('&', title));

        p.openInventory(inv);
    }
}
