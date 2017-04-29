package be.woutdev.megawalls.plugin.wither;

import java.util.UUID;

/**
 * Created by Wout on 22/08/2016.
 */
public interface NMSWither
{
    void register();

    void spawn();

    void remove();

    double getWitherHealth();

    String getWitherName();

    UUID getEntityId();

    String getWorldName();
}
