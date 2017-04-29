package be.woutdev.megawalls.plugin.scoreboard.objects;

import be.woutdev.megawalls.plugin.scoreboard.UserScoreboard;
import be.woutdev.megawalls.plugin.scoreboard.listener.ScoreboardUpdateListener;
import org.bukkit.Bukkit;

/**
 * Created by Wout on 16/04/2016.
 */
public abstract class ScoreboardChangeableObject extends ScoreboardIndexableObject
{
    private ScoreboardUpdateListener[] listeners;
    private int taskId;

    public ScoreboardChangeableObject(UserScoreboard board, ScoreboardObjectType type, String value, ScoreboardUpdateListener... updateListeners)
    {
        super(board, type, value);

        this.listeners = updateListeners;
    }

    /**
     * Start the entry
     */
    public abstract void start();

    /**
     * Pause the entry
     */
    public void pause()
    {
        Bukkit.getScheduler().cancelTask(taskId);
        taskId = 0;
    }

    /**
     * Get the taskId of the entry
     *
     * @return The taskId
     */
    public int getTaskId()
    {
        return taskId;
    }

    /**
     * Set the taskId of the entry
     *
     * @param taskId The taskId to set
     */
    protected void setTaskId(int taskId)
    {
        this.taskId = taskId;
    }

    /**
     * Get all listener listening for a change
     *
     * @return An array of listener listening
     */
    public ScoreboardUpdateListener[] getListeners()
    {
        return listeners;
    }
}
