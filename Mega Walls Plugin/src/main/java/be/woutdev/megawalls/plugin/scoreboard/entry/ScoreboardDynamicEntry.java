package be.woutdev.megawalls.plugin.scoreboard.entry;

import be.woutdev.megawalls.plugin.scoreboard.UserScoreboard;
import be.woutdev.megawalls.plugin.scoreboard.listener.ScoreboardUpdateListener;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardDynamicObject;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardObjectType;

/**
 * Created by Wout on 16/04/2016.
 */
public class ScoreboardDynamicEntry extends ScoreboardDynamicObject
{
    /**
     * Create a new ScoreboardDynamicEntry which will be an entry that can have dynamic text
     *
     * @param board The board where this entry should be placed on
     * @param ticks The amount of ticks between changes
     * @param random If the changes should be random or not
     * @param values The values you want to change between
     * @param listeners Some listener you may want to add
     */
    public ScoreboardDynamicEntry(UserScoreboard board, int ticks, boolean random, String[] values, ScoreboardUpdateListener[] listeners)
    {
        super(board, ScoreboardObjectType.ENTRY, ticks, random, values, listeners);
    }
}
