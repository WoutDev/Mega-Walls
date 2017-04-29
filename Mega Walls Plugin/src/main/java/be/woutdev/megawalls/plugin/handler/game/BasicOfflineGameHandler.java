package be.woutdev.megawalls.plugin.handler.game;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.handler.OfflineGameHandler;
import be.woutdev.megawalls.api.model.Game;
import be.woutdev.megawalls.plugin.model.BasicGame;

import java.util.List;

/**
 * Created by Wout on 20/08/2016.
 */
public class BasicOfflineGameHandler implements OfflineGameHandler
{

    @Override
    public Game findById(int id)
    {
        return null;
    }

    @Override
    public Game insert(Game object)
    {
        ((BasicGame) object).setId(MWAPI.getGameHandler().findAll().size());

        return object;
    }

    @Override
    public Game update(Game object)
    {
        return null;
    }

    @Override
    public void delete(Game object)
    {

    }

    @Override
    public List<Game> findAll()
    {
        return null;
    }
}
