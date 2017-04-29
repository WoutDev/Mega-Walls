package be.woutdev.megawalls.api.model;

import be.woutdev.megawalls.api.enums.GameState;
import be.woutdev.megawalls.api.enums.TeamColor;

import java.util.Date;
import java.util.Set;

/**
 * Created by Wout on 19/08/2016.
 */
public interface Game
{
    int getId();

    Map getMap();

    GameState getState();

    Set<User> getSpectators();

    Set<Team> getTeams();

    Date getStartedAt();

    Date getEndedAt();

    TeamColor getWinner();

    boolean join(User user);

    boolean leave(User user);

    boolean spectate(User user);

    boolean isPlaying(User user);

    Team getTeam(User user);

    void end(TeamColor winner);

    void start();

    boolean isSpectating(User user);

    Set<User> getPlayers();

    void sendMessage(String message);
}
