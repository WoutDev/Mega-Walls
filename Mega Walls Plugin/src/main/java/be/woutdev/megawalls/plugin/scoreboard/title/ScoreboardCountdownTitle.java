package be.woutdev.megawalls.plugin.scoreboard.title;

import be.woutdev.megawalls.plugin.scoreboard.UserScoreboard;
import be.woutdev.megawalls.plugin.scoreboard.listener.ScoreboardUpdateListener;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardCountObject;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardCountType;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardObjectType;

/**
 * Created by Wout on 16/04/2016.
 */
public class ScoreboardCountdownTitle extends ScoreboardCountObject
{
    /**
     * Create a new ScoreboardCountdownTitle which will be an title with an countdown on it
     *
     * @param board The board
     * @param millis The amount of millis
     * @param value The value
     * @param listeners The listener
     */
    public ScoreboardCountdownTitle(UserScoreboard board, int millis, String value, ScoreboardUpdateListener... listeners)
    {
        super(board, ScoreboardObjectType.TITLE, millis, ScoreboardCountType.DOWN, value, listeners);
    }
}
