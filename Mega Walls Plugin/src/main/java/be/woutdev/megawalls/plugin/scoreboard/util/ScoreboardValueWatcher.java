package be.woutdev.megawalls.plugin.scoreboard.util;

import be.woutdev.megawalls.plugin.scoreboard.UserScoreboard;

/**
 * Created by Wout on 4/07/2016.
 */
public interface ScoreboardValueWatcher<T>
{
    T get(UserScoreboard scoreboard);
}
