package be.woutdev.megawalls.plugin.kit;

import be.woutdev.megawalls.api.kit.KitItem;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Wout on 30/04/2017.
 */
public class BasicKitItem implements KitItem
{
    private final ItemStack stack;
    private final boolean droppable;
    private final boolean unbreakable;

    public BasicKitItem(ItemStack stack, boolean droppable, boolean unbreakable)
    {
        this.stack = stack;
        this.droppable = droppable;
        this.unbreakable = unbreakable;
    }

    @Override
    public ItemStack getStack()
    {
        return stack;
    }

    @Override
    public boolean isDroppable()
    {
        return droppable;
    }

    @Override
    public boolean isUnbreakable()
    {
        return unbreakable;
    }
}
