package be.woutdev.megawalls.plugin.scoreboard.objects;

import be.woutdev.megawalls.plugin.scoreboard.UserScoreboard;
import be.woutdev.megawalls.plugin.scoreboard.listener.ScoreboardUpdateListener;
import be.woutdev.megawalls.plugin.scoreboard.util.ScoreboardValueWatcher;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by Wout on 16/04/2016.
 */
public abstract class ScoreboardObjectBuilder
{
    private UserScoreboard board;
    private ScoreboardObjectType objectType;
    private boolean isCount;
    private boolean isDynamic;
    private boolean isRandom;
    private List<String> values;
    private List<ScoreboardUpdateListener> listeners;
    private ScoreboardValueWatcher watcher;
    private ScoreboardCountType countType;
    private int millis;
    private int ticks;

    public ScoreboardObjectBuilder(UserScoreboard board, ScoreboardObjectType objectType)
    {
        this.board = board;
        this.objectType = objectType;
        this.isCount = false;
        this.isDynamic = false;
        this.isRandom = false;
        this.values = Lists.newArrayList();
        this.listeners = Lists.newArrayList();
        this.watcher = null;
        this.countType = ScoreboardCountType.DOWN;
        this.millis = 0;
        this.ticks = 20;
    }

    /**
     * Set if this object will be an counter or not
     *
     * @param isCount Counter or not
     *
     * @return The instance
     */
    public ScoreboardObjectBuilder count(boolean isCount)
    {
        this.isCount = isCount;

        return this;
    }

    /**
     * Set if this object will be an dynamic entry or not
     *
     * @param isDynamic Dynamic or not
     *
     * @return The instance
     */
    public ScoreboardObjectBuilder dynamic(boolean isDynamic)
    {
        this.isDynamic = isDynamic;

        return this;
    }

    /**
     * Set if the dynamic entries should be changed randomly or not.
     *
     * @param isRandom Change random or not
     *
     * @return The instance
     */
    public ScoreboardObjectBuilder random(boolean isRandom)
    {
        this.isRandom = isRandom;

        return this;
    }

    /**
     * Set the values for this dynamic entry
     *
     * @param values The values to set
     *
     * @return The instance
     */
    public ScoreboardObjectBuilder values(List<String> values)
    {
        this.values = values;

        return this;
    }

    /**
     * Set the listener for this changeable entry
     *
     * @param listeners The listener that will listen for changes
     *
     * @return The instance
     */
    public ScoreboardObjectBuilder listeners(List<ScoreboardUpdateListener> listeners)
    {
        this.listeners = listeners;

        return this;
    }

    /**
     * Set the counttype, either UP or DOWN.
     *
     * @param countType The counttype to use
     *
     * @return The instance
     */
    public ScoreboardObjectBuilder countType(ScoreboardCountType countType)
    {
        this.countType = countType;

        return this;
    }

    /**
     * Set the amount of millis the counter will count to or from depending on the type
     *
     * @param millis The amount of millis
     *
     * @return The instance
     */
    public ScoreboardObjectBuilder millis(int millis)
    {
        this.millis = millis;

        return this;
    }

    /**
     * Set the amount of ticks between dynamic entry changes
     *
     * @param ticks The amount of ticks
     *
     * @return The instance
     */
    public ScoreboardObjectBuilder ticks(int ticks)
    {
        this.ticks = ticks;

        return this;
    }

    /**
     * Add a value
     *
     * @param value The value to add
     *
     * @return The instance
     */
    public ScoreboardObjectBuilder withValue(String value)
    {
        values.add(value);

        return this;
    }

    /**
     * Set the only value
     *
     * @param value The only value to add
     *
     * @return The instance
     */
    public ScoreboardObjectBuilder onlyValue(String value)
    {
        values.clear();
        values.add(value);

        return this;
    }

    /**
     * Add an new listener
     *
     * @param listener The listener to add
     *
     * @return The instance
     */
    public ScoreboardObjectBuilder withListener(ScoreboardUpdateListener listener)
    {
        listeners.add(listener);

        return this;
    }

    /**
     * Add a new value watcher
     *
     * @param watcher The watcher to add
     *
     * @return The instance
     */
    public ScoreboardObjectBuilder withValueWatcher(ScoreboardValueWatcher watcher)
    {
        this.watcher = watcher;

        return this;
    }

    /**
     * Get the ticks
     *
     * @return the ticks
     */
    public int getTicks()
    {
        return ticks;
    }

    /**
     * Get the millis
     *
     * @return the millis
     */
    public int getMillis()
    {
        return millis;
    }

    /**
     * Get the counttype
     *
     * @return the counttype
     */
    public ScoreboardCountType getCountType()
    {
        return countType;
    }

    /**
     * Get the listener
     *
     * @return the listener
     */
    public List<ScoreboardUpdateListener> getListeners()
    {
        return listeners;
    }

    /**
     * Get all the values
     *
     * @return The values
     */
    public List<String> getValues()
    {
        return values;
    }

    /**
     * Get if its random or not
     *
     * @return Random or not
     */
    public boolean isRandom()
    {
        return isRandom;
    }

    /**
     * Get if its dynamic or not
     *
     * @return Dynamic or not
     */
    public boolean isDynamic()
    {
        return isDynamic;
    }

    /**
     * Get if its an counter or not
     *
     * @return Counter or not
     */
    public boolean isCount()
    {
        return isCount;
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
     * Get the object type, TITLE or ENTRY
     *
     * @return The type of the object
     */
    public ScoreboardObjectType getObjectType()
    {
        return objectType;
    }

    /**
     * Get the board
     *
     * @return The board
     */
    public UserScoreboard getBoard()
    {
        return board;
    }

    /**
     * Create the object
     *
     * @return The ScoreboardObject which you now can add to the scoreboard as title or entry
     */
    public abstract ScoreboardObject create();
}
