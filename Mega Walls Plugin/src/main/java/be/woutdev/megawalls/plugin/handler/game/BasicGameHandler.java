package be.woutdev.megawalls.plugin.handler.game;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.enums.GameState;
import be.woutdev.megawalls.api.handler.GameHandler;
import be.woutdev.megawalls.api.model.Game;
import be.woutdev.megawalls.api.model.Map;
import be.woutdev.megawalls.plugin.model.BasicGame;
import be.woutdev.megawalls.plugin.wither.NMSWither;
import be.woutdev.megawalls.plugin.wither.v1_9_R1.Wither191;
import org.bukkit.Bukkit;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Wout on 20/08/2016.
 */
public class BasicGameHandler implements GameHandler
{
    private final Set<Game> games;
    private NMSWither witherType;

    public BasicGameHandler()
    {
        this.games = new HashSet<Game>();

        String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];

        switch (version)
        {
            case "v1_9_R1":
                witherType = new Wither191(null, Bukkit.getWorlds().get(0).getSpawnLocation());
                break;
        }

        if (witherType == null)
        {
            MWAPI.getPlugin().getLogger().severe("Failed to initialize custom wither. Disabling plugin...");
            Bukkit.getServer().getPluginManager().disablePlugin(MWAPI.getPlugin());
            return;
        }

        witherType.register();
    }

    @Override
    public Game createGame(Map map)
    {
        Game game = null;

        if (map.isConfigured() && map.isActive())
        {
            game = new BasicGame(map);
            games.add(game);

            MWAPI.getOfflineGameHandler().insert(game);
        }

        return game;
    }

    @Override
    public Set<Game> findAll()
    {
        return games;
    }

    @Override
    public Set<Game> findByState(GameState state)
    {
        return games.stream().filter(g -> g.getState().equals(state)).collect(Collectors.toSet());
    }

    @Override
    public Set<Game> findActive()
    {
        return games.stream()
                    .filter(g -> g.getState().equals(GameState.LOBBY) || g.getState().equals(GameState.PRE_GAME) ||
                                 g.getState().equals(GameState.MAIN_GAME) || g.getState().equals(GameState.END_GAME))
                    .collect(Collectors.toSet());
    }

    @Override
    public Game findById(int id)
    {
        return games.stream().filter(g -> g.getId() == id).findAny().orElse(null);
    }

    public Class getWitherType()
    {
        return witherType.getClass();
    }
}
