package be.woutdev.megawalls.plugin.scoreboard.title;

import be.woutdev.megawalls.plugin.scoreboard.UserScoreboard;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardObject;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardObjectType;

/**
 * Created by Wout on 16/04/2016.
 */
public class ScoreboardTitle extends ScoreboardObject
{
    /**
     * Create a normal ScoreboardTitle with text on it
     *
     * @param board The board
     * @param value The value
     */
    public ScoreboardTitle(UserScoreboard board, String value)
    {
        super(board, ScoreboardObjectType.TITLE, value);
    }
}
