package be.woutdev.megawalls.plugin.kit;

import be.woutdev.megawalls.api.kit.Kit;
import be.woutdev.megawalls.api.kit.KitItem;
import org.bukkit.potion.PotionEffect;

import java.util.Map;
import java.util.Set;

/**
 * Created by Wout on 30/04/2017.
 */
public class BasicKit implements Kit
{
    private final String name;
    private final KitItem helmet;
    private final KitItem chestplate;
    private final KitItem pants;
    private final KitItem boots;
    private final Map<Integer, KitItem> items;
    private final Set<PotionEffect> effects;

    public BasicKit(String name, KitItem helmet, KitItem chestplate, KitItem pants, KitItem boots, Map<Integer, KitItem> items, Set<PotionEffect> effects)
    {
        this.name = name;
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.pants = pants;
        this.boots = boots;
        this.items = items;
        this.effects = effects;
    }

    @Override
    public KitItem getHelmet()
    {
        return helmet;
    }

    @Override
    public KitItem getChestPlate()
    {
        return chestplate;
    }

    @Override
    public KitItem getPants()
    {
        return pants;
    }

    @Override
    public KitItem getBoots()
    {
        return boots;
    }

    @Override
    public KitItem getItemAt(int index)
    {
        return items.getOrDefault(index, null);
    }

    @Override
    public Map<Integer, KitItem> getItems()
    {
        return items;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public Set<PotionEffect> getEffects()
    {
        return effects;
    }
}
