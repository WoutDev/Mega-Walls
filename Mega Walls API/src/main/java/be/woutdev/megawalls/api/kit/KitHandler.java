package be.woutdev.megawalls.api.kit;

import java.util.Set;

/**
 * Created by Wout on 30/04/2017.
 */
public interface KitHandler
{
    Set<Kit> getKits();

    void registerKit(Kit kit);

    void unregisterKit(Kit kit);

    Kit getKitByName(String name);
}
