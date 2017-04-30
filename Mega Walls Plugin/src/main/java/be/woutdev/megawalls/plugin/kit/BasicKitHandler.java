package be.woutdev.megawalls.plugin.kit;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.kit.Kit;
import be.woutdev.megawalls.api.kit.KitHandler;
import be.woutdev.megawalls.api.kit.KitItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Wout on 30/04/2017.
 */
public class BasicKitHandler implements KitHandler
{
    private final Set<Kit> kits;

    // TODO: Move default kits into configuration, allow modification and adding new simple kits
    public BasicKitHandler()
    {
        this.kits = new HashSet<>();

        if (MWAPI.getConfig().isDefaultKitsEnabled())
        {
            // *** FIGHTER KIT *** //

            // Fighter Armor
            ItemStack fighterHelmet = new ItemStack(Material.IRON_HELMET, 1);
            fighterHelmet.addEnchantment(Enchantment.DAMAGE_ALL, 1);
            KitItem fighterHelmetKitItem = MWAPI.getKitItemBuilder()
                                                .stack(fighterHelmet)
                                                .drop(false)
                                                .unbreakable(true)
                                                .create();

            ItemStack fighterChestplate = new ItemStack(Material.IRON_CHESTPLATE, 1);
            fighterChestplate.addEnchantment(Enchantment.DAMAGE_ALL, 1);
            KitItem fighterChestplateKitItem = MWAPI.getKitItemBuilder()
                                                    .stack(fighterChestplate)
                                                    .drop(false)
                                                    .unbreakable(true)
                                                    .create();

            ItemStack fighterPants = new ItemStack(Material.IRON_LEGGINGS, 1);
            fighterPants.addEnchantment(Enchantment.DAMAGE_ALL, 1);
            KitItem fighterPantsKitItem = MWAPI.getKitItemBuilder()
                                               .stack(fighterPants)
                                               .drop(false)
                                               .unbreakable(true)
                                               .create();

            ItemStack fighterBoots = new ItemStack(Material.IRON_BOOTS, 1);
            fighterBoots.addEnchantment(Enchantment.DAMAGE_ALL, 1);
            KitItem fighterBootsKitItem = MWAPI.getKitItemBuilder()
                                               .stack(fighterPants)
                                               .drop(false)
                                               .unbreakable(true)
                                               .create();

            // Fighter Items
            ItemStack fighterSword = new ItemStack(Material.IRON_SWORD, 1);
            fighterSword.addEnchantment(Enchantment.DAMAGE_ALL, 2);
            KitItem fighterSwordKitItem = MWAPI.getKitItemBuilder()
                                               .stack(fighterSword)
                                               .drop(false)
                                               .unbreakable(true)
                                               .create();

            ItemStack fighterFood = new ItemStack(Material.GOLDEN_APPLE, 10);
            KitItem fighterFoodKitItem = MWAPI.getKitItemBuilder()
                                              .stack(fighterSword)
                                              .drop(true)
                                              .unbreakable(true)
                                              .create();

            registerKit(MWAPI.getKitBuilder("Fighter")
                             .setBoots(fighterBootsKitItem)
                             .setPants(fighterPantsKitItem)
                             .setChestPlate(fighterChestplateKitItem)
                             .setHelmet(fighterHelmetKitItem)
                             .setItemAt(0, fighterSwordKitItem)
                             .setItemAt(1, fighterFoodKitItem)
                             .create());
        }
    }

    @Override
    public Set<Kit> getKits()
    {
        return kits;
    }

    @Override
    public void registerKit(Kit kit)
    {
        kits.add(kit);
    }

    @Override
    public void unregisterKit(Kit kit)
    {
        kits.remove(kit);
    }

    @Override
    public Kit getKitByName(String name)
    {
        return kits.stream().filter(k -> k.getName().equalsIgnoreCase(name)).findAny().orElse(null);
    }
}
