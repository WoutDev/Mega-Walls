package be.woutdev.megawalls.plugin.scoreboard.title;

import be.woutdev.megawalls.plugin.scoreboard.UserScoreboard;
import be.woutdev.megawalls.plugin.scoreboard.listener.ScoreboardUpdateListener;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardDynamicObject;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardObjectType;

/**
 * Created by Wout on 16/04/2016.
 */
public class ScoreboardDynamicTitle extends ScoreboardDynamicObject
{
    /**
     * Create a new ScoreboardDynamicTitle which will be an title with dynamic text on it
     *
     * @param board The board
     * @param ticks The amount of ticks
     * @param random Change randomly or not
     * @param values The values to change between
     * @param listeners The listener
     */
    public ScoreboardDynamicTitle(UserScoreboard board, int ticks, boolean random, String[] values, ScoreboardUpdateListener[] listeners)
    {
        super(board, ScoreboardObjectType.TITLE, ticks, random, values, listeners);
    }
}
