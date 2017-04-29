package be.woutdev.megawalls.plugin.scoreboard.objects;

import be.woutdev.megawalls.plugin.scoreboard.UserScoreboard;

/**
 * Created by Wout on 24/04/2016.
 */
public class ScoreboardIndexableObject extends ScoreboardObject
{
    private int index;

    public ScoreboardIndexableObject(UserScoreboard board, ScoreboardObjectType type, String value)
    {
        super(board, type, value);

        this.index = -1;
    }

    /**
     * Get the index on the scoreboard, -1 when its not yet added.
     *
     * @return The index on the scoreboard
     */
    public int getIndex()
    {
        return index;
    }

    /**
     * Set the index on the scoreboard
     *
     * @param index The index on the scoreboard
     */
    public void setIndex(int index)
    {
        this.index = index;
    }
}
