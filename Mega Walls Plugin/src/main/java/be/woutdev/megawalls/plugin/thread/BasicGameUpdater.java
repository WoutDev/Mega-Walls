package be.woutdev.megawalls.plugin.thread;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.enums.GameState;
import be.woutdev.megawalls.api.handler.GameHandler;
import be.woutdev.megawalls.api.handler.OfflineGameHandler;
import be.woutdev.megawalls.api.model.Game;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Wout on 20/08/2016.
 */
public class BasicGameUpdater extends BukkitRunnable
{
    private final GameHandler handler;
    private final OfflineGameHandler offlineGameHandler;

    public BasicGameUpdater()
    {
        handler = MWAPI.getGameHandler();
        offlineGameHandler = MWAPI.getOfflineGameHandler();
    }

    @Override
    public void run()
    {
        for (Game game : handler.findAll())
        {
            if (game.getState() != GameState.ENDED)
            {
                offlineGameHandler.update(game);
            }
        }
    }
}
