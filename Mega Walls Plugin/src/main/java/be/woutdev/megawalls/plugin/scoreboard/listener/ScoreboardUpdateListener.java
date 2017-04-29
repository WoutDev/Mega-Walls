package be.woutdev.megawalls.plugin.scoreboard.listener;

import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardChangeableObject;

/**
 * Created by Wout on 16/04/2016.
 */
public interface ScoreboardUpdateListener
{
    /**
     * Called whenever an ScoreboardChangeableObject is changed
     *
     * @param object The object which changed
     * @param type The type of change that occured
     */
    public void update(ScoreboardChangeableObject object, ScoreboardUpdateType type);
}
