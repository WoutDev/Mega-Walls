package be.woutdev.megawalls.plugin.kit;

import be.woutdev.megawalls.api.kit.KitItem;
import be.woutdev.megawalls.api.kit.KitItemBuilder;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Wout on 30/04/2017.
 */
public class BasicKitItemBuilder implements KitItemBuilder
{
    private ItemStack stack;
    private boolean drop;
    private boolean unbreakable;

    @Override
    public KitItemBuilder stack(ItemStack stack)
    {
        this.stack = stack;

        return this;
    }

    @Override
    public KitItemBuilder drop(boolean drop)
    {
        this.drop = drop;

        return this;
    }

    @Override
    public KitItemBuilder unbreakable(boolean unbreakable)
    {
        this.unbreakable = unbreakable;

        return this;
    }

    @Override
    public KitItem create()
    {
        return new BasicKitItem(stack, drop, unbreakable);
    }
}
