package be.woutdev.megawalls.plugin.scoreboard.objects;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.plugin.scoreboard.UserScoreboard;
import be.woutdev.megawalls.plugin.scoreboard.listener.ScoreboardUpdateListener;
import be.woutdev.megawalls.plugin.scoreboard.listener.ScoreboardUpdateType;
import org.bukkit.Bukkit;

import java.util.Random;

/**
 * Created by Wout on 16/04/2016.
 */
public abstract class ScoreboardDynamicObject extends ScoreboardChangeableObject
{
    private static final Random randomObj = new Random();
    private String[] values;
    private int ticks;
    private boolean random;
    private int dynamicIndex;

    public ScoreboardDynamicObject(UserScoreboard board, ScoreboardObjectType type, int ticks, boolean random, String[] values, ScoreboardUpdateListener[] listeners)
    {
        super(board, type, values[0], listeners);

        this.values = values;
        this.ticks = ticks;
        this.random = random;
    }

    /**
     * Start the dynamic entry
     */
    @Override
    public void start()
    {
        setTaskId(Bukkit.getScheduler().runTaskTimerAsynchronously(MWAPI.getPlugin(), new Runnable()
        {
            @Override
            public void run()
            {
                if (isRandom())
                {
                    setValue(getValues()[getRandom().nextInt(getValues().length)]);
                }
                else
                {
                    setValue(getValues()[getDynamicIndex()]);
                    nextIndex();
                }

                if (getType().equals(ScoreboardObjectType.TITLE))
                {
                    getBoard().setTitle(getInstance());
                }

                if (getListeners() != null)
                {
                    for (ScoreboardUpdateListener listener : getListeners())
                    {
                        listener.update((ScoreboardDynamicObject) getInstance(), ScoreboardUpdateType.CHANGED);
                    }
                }
            }
        }, 0L, getTicks()).getTaskId());
    }

    /**
     * Get the random object
     *
     * @return The random instance
     */
    public Random getRandom()
    {
        return randomObj;
    }

    /**
     * Get the amount of ticks between changes
     *
     * @return The amount of ticks between changes
     */
    public int getTicks()
    {
        return ticks;
    }

    /**
     * See if this Dynamic entry is changed randomly or not
     *
     * @return If the changes are random or not
     */
    public boolean isRandom()
    {
        return random;
    }

    /**
     * Get the current index
     *
     * @return The current index
     */
    public int getDynamicIndex()
    {
        return dynamicIndex;
    }

    /**
     * Go to the next index
     */
    public void nextIndex()
    {
        if (dynamicIndex == values.length - 1)
        {
            dynamicIndex = 0;
        }
        else
        {
            dynamicIndex++;
        }
    }

    /**
     * Get all the values
     *
     * @return An array of values
     */
    public String[] getValues()
    {
        return values;
    }
}
