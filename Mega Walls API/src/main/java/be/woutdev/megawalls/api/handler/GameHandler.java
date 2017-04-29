package be.woutdev.megawalls.api.handler;

import be.woutdev.megawalls.api.enums.GameState;
import be.woutdev.megawalls.api.model.Game;
import be.woutdev.megawalls.api.model.Map;

import java.util.Set;

/**
 * Created by Wout on 20/08/2016.
 */
public interface GameHandler
{
    Game createGame(Map map);

    Set<Game> findAll();

    Set<Game> findByState(GameState state);

    Set<Game> findActive();

    Game findById(int id);
}
