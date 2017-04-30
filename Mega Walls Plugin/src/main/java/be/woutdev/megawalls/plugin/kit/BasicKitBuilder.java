package be.woutdev.megawalls.plugin.kit;

import be.woutdev.megawalls.api.kit.Kit;
import be.woutdev.megawalls.api.kit.KitBuilder;
import be.woutdev.megawalls.api.kit.KitItem;
import org.bukkit.potion.PotionEffect;

import java.util.Map;
import java.util.Set;

/**
 * Created by Wout on 30/04/2017.
 */
public class BasicKitBuilder implements KitBuilder
{
    private String name;
    private KitItem helmet;
    private KitItem chestplate;
    private KitItem pants;
    private KitItem boots;
    private Map<Integer, KitItem> items;
    private Set<PotionEffect> effects;

    public BasicKitBuilder(String name)
    {
        this.name = name;
    }

    @Override
    public KitBuilder setHelmet(KitItem item)
    {
        helmet = item;

        return this;
    }

    @Override
    public KitBuilder setChestPlate(KitItem item)
    {
        chestplate = item;

        return this;
    }

    @Override
    public KitBuilder setPants(KitItem item)
    {
        pants = item;

        return this;
    }

    @Override
    public KitBuilder setBoots(KitItem item)
    {
        boots = item;

        return this;
    }

    @Override
    public KitBuilder setItemAt(int index, KitItem item)
    {
        items.put(index, item);

        return this;
    }

    @Override
    public KitBuilder addEffect(PotionEffect effect)
    {
        effects.add(effect);

        return this;
    }

    @Override
    public Kit create()
    {
        return new BasicKit(name, helmet, chestplate, pants, boots, items, effects);
    }
}
