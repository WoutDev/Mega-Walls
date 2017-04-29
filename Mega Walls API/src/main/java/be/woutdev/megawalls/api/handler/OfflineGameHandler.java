package be.woutdev.megawalls.api.handler;

import be.woutdev.megawalls.api.model.Game;

/**
 * Created by Wout on 20/08/2016.
 */
public interface OfflineGameHandler extends OfflineHandler<Game>
{
    Game findById(int id);
}
