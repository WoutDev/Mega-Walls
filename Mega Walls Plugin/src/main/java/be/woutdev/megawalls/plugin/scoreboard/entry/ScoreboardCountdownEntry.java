package be.woutdev.megawalls.plugin.scoreboard.entry;

import be.woutdev.megawalls.plugin.scoreboard.UserScoreboard;
import be.woutdev.megawalls.plugin.scoreboard.listener.ScoreboardUpdateListener;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardCountObject;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardCountType;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardObjectType;

/**
 * Created by Wout on 16/04/2016.
 */
public class ScoreboardCountdownEntry extends ScoreboardCountObject
{
    /**
     * Create a new ScoreboardCountdownEntry which will be an scoreboard entry with an countdown on it
     *
     * @param board The board where this entry should be placed on
     * @param millis The amount of millis the countdown should count from
     * @param value The entry value, containing the placeholder for the countdown (%count%)
     * @param listeners Some listener you may want to add
     */
    public ScoreboardCountdownEntry(UserScoreboard board, int millis, String value, ScoreboardUpdateListener... listeners)
    {
        super(board, ScoreboardObjectType.ENTRY, millis, ScoreboardCountType.DOWN, value, listeners);
    }
}
