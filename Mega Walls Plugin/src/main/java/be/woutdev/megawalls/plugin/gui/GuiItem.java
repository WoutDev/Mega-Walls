package be.woutdev.megawalls.plugin.gui;

import org.bukkit.inventory.ItemStack;

/**
 * Created by Wout on 30/04/2017.
 */
public class GuiItem
{
    private final int index;
    private final ItemStack stack;
    private final ClickListener listener;

    public GuiItem(int index, ItemStack stack, ClickListener listener)
    {
        this.index = index;
        this.stack = stack;
        this.listener = listener;
    }

    public int getIndex()
    {
        return index;
    }

    public ItemStack getStack()
    {
        return stack;
    }

    public ClickListener getListener()
    {
        return listener;
    }
}
