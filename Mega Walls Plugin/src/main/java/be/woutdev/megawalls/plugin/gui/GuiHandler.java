package be.woutdev.megawalls.plugin.gui;

import com.google.common.collect.Sets;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Set;

/**
 * Created by Wout on 30/04/2017.
 */
public class GuiHandler implements Listener
{
    private final Set<Gui> guis;

    public GuiHandler()
    {
        this.guis = Sets.newHashSet();
    }

    public Set<Gui> getGuis()
    {
        return guis;
    }

    public void openGui(Player p, String name)
    {
        getGuis().stream().filter(g -> g.getName().equalsIgnoreCase(name)).findAny().ifPresent(g -> g.open(p));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e)
    {
        
    }
}
