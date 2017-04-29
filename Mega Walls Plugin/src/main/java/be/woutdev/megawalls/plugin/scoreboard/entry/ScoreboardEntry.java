package be.woutdev.megawalls.plugin.scoreboard.entry;

import be.woutdev.megawalls.plugin.scoreboard.UserScoreboard;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardIndexableObject;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardObjectType;

/**
 * Created by Wout on 16/04/2016.
 */
public class ScoreboardEntry extends ScoreboardIndexableObject
{
    /**
     * Create a new basic ScoreboardEntry with basic text
     *
     * @param board The board where this entry should be placed on
     * @param value The value of this entry
     */
    public ScoreboardEntry(UserScoreboard board, String value)
    {
        super(board, ScoreboardObjectType.ENTRY, value);
    }
}
