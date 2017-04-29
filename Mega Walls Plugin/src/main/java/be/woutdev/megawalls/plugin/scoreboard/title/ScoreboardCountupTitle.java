package be.woutdev.megawalls.plugin.scoreboard.title;

import be.woutdev.megawalls.plugin.scoreboard.UserScoreboard;
import be.woutdev.megawalls.plugin.scoreboard.listener.ScoreboardUpdateListener;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardCountObject;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardCountType;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardObjectType;

/**
 * Created by Wout on 16/04/2016.
 */
public class ScoreboardCountupTitle extends ScoreboardCountObject
{
    /**
     * Create a new ScoreboardCountupTitle that will count up
     *
     * @param board The board where this countup will be present
     * @param millis The amount of millis where to stop
     * @param value The value, including the placeholder: %count%
     * @param listeners You may want to add a listener for when its done
     */
    public ScoreboardCountupTitle(UserScoreboard board, int millis, String value, ScoreboardUpdateListener... listeners)
    {
        super(board, ScoreboardObjectType.TITLE, millis, ScoreboardCountType.UP, value, listeners);
    }
}
