package be.woutdev.megawalls.api.model;

import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by Wout on 19/08/2016.
 */
public interface User
{
    UUID getUniqueId();

    boolean isPlaying();

    boolean isSpectating();

    Game getGame();

    Team getTeam();

    Player getPlayer();
}
