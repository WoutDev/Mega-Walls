package be.woutdev.megawalls.plugin.scoreboard.objects;

import be.woutdev.megawalls.plugin.scoreboard.UserScoreboard;
import be.woutdev.megawalls.plugin.scoreboard.util.ScoreboardValueWatcher;
import org.bukkit.ChatColor;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Wout on 16/04/2016.
 */
public abstract class ScoreboardObject
{
    private ScoreboardObject instance;
    private UserScoreboard board;
    private ScoreboardObjectType type;
    private int id;
    private String value;
    private boolean changed;
    private String previousValue;
    private ScoreboardValueWatcher watcher;

    public ScoreboardObject(UserScoreboard board, ScoreboardObjectType type, String value)
    {
        this.board = board;
        this.id = ThreadLocalRandom.current().nextInt(0, 1000000);
        this.type = type;
        this.value = value;
        this.changed = false;
        this.previousValue = value;
        this.instance = this;
    }

    /**
     * Get the type of this ScoreboardObject
     *
     * @return The type
     */
    public ScoreboardObjectType getType()
    {
        return type;
    }

    /**
     * Get the current value of this ScoreboardObject
     *
     * @return The current value
     */
    public String getValue()
    {
        return ChatColor.translateAlternateColorCodes('&', value);
    }

    /**
     * Set the current value of this ScoreboardObject
     *
     * @param value The value
     */
    public void setValue(String value)
    {
        this.previousValue = this.value;
        this.value = value;
        this.changed = true;
    }

    /**
     * Get the unique id of this object
     *
     * @return The unique id
     */
    public int getId()
    {
        return id;
    }

    /**
     * Get if this object has been changed between 2 scoreboard runs
     *
     * @return If this object was changed or not between two scoreboard runs
     */
    public boolean isChanged()
    {
        return changed;
    }

    /**
     * Set if this object has been changed between 2 scoreboard runs
     *
     * @param changed True or false if changed or not
     */
    public void setChanged(boolean changed)
    {
        this.changed = changed;
    }

    /**
     * Remove this object from the scoreboard <br> For titles it will set the default title back <br> For entries it
     * will remove the entry from the scoreboard <br>
     */
    public void remove()
    {
        if (getType().equals(ScoreboardObjectType.ENTRY))
        {
            getBoard().removeEntryById(getId());
        }
        else
        {
            getBoard().setTitle(getBoard().getTitleBuilder().onlyValue("&eMega Walls").create());
        }

        if (this instanceof ScoreboardChangeableObject)
        {
            ScoreboardChangeableObject object = (ScoreboardChangeableObject) this;

            if (object.getTaskId() != 0)
            {
                object.pause();
            }
        }
    }

    /**
     * Get the previous value before it was overwritten by another one.
     *
     * @return The previous value
     */
    public String getPreviousValue()
    {
        return previousValue;
    }

    /**
     * Get the ScoreboardValueWatcher if present.
     *
     * @return The ScoreboardValueWatcher or null.
     */
    public ScoreboardValueWatcher getWatcher()
    {
        return watcher;
    }

    /**
     * Set the ScoreboardValueWatcher
     *
     * @param watcher The watcher to set
     */
    public void setWatcher(ScoreboardValueWatcher watcher)
    {
        this.watcher = watcher;
    }

    /**
     * Get the board this object belongs to
     *
     * @return The board
     */
    public UserScoreboard getBoard()
    {
        return board;
    }

    /**
     * Set the board instance for this ScoreboardObject
     *
     * @param board The board instance to set
     */
    public void setBoard(UserScoreboard board)
    {
        this.board = board;
    }

    /**
     * Get the instance for this object
     *
     * @return The instance
     */
    public ScoreboardObject getInstance()
    {
        return instance;
    }
}
