package be.woutdev.megawalls.plugin.scoreboard.objects;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.plugin.scoreboard.UserScoreboard;
import be.woutdev.megawalls.plugin.scoreboard.listener.ScoreboardUpdateListener;
import be.woutdev.megawalls.plugin.scoreboard.listener.ScoreboardUpdateType;
import org.bukkit.Bukkit;

/**
 * Created by Wout on 16/04/2016.
 */
public abstract class ScoreboardCountObject extends ScoreboardChangeableObject
{
    private int millis;
    private String startValue;
    private int upTime;
    private ScoreboardCountType countType;
    private int beginMillis;

    public ScoreboardCountObject(UserScoreboard board, ScoreboardObjectType objectType, int millis, ScoreboardCountType type, String value, ScoreboardUpdateListener... listeners)
    {
        super(board, objectType, value, listeners);

        this.millis = millis;
        this.beginMillis = millis;
        this.countType = type;
        this.startValue = value;
    }

    /**
     * Get the amount of millis this count object is counting to or from
     *
     * @return The amount of millis
     */
    public int getMillis()
    {
        return millis;
    }

    /**
     * Set the amount of millis this count object is counting to or from
     *
     * @param millis The amount of millis
     */
    public void setMillis(int millis)
    {
        this.millis = millis;
    }

    /**
     * Get the begin millis, set at constructor
     *
     * @return The begin millis
     */
    public int getBeginMillis()
    {
        return beginMillis;
    }

    /**
     * Get the time this UP count object has been counting up
     *
     * @return The amount of millis that this UP count object has been counting up
     */
    public int getUpTime()
    {
        return upTime;
    }

    /**
     * Get the start value string, containing the raw placeholder
     *
     * @return The raw start value
     */
    public String getStartValue()
    {
        return startValue;
    }

    /**
     * Start the counter
     */
    @Override
    public void start()
    {
        setTaskId(Bukkit.getScheduler().runTaskTimerAsynchronously(MWAPI.getPlugin(), new Runnable()
        {
            @Override
            public void run()
            {
                if (getCountType() == ScoreboardCountType.DOWN)
                {
                    if (millis <= 0)
                    {
                        Bukkit.getScheduler().cancelTask(getTaskId());

                        if (getListeners() != null)
                        {
                            for (ScoreboardUpdateListener listener : getListeners())
                            {
                                listener.update((ScoreboardCountObject) getInstance(), ScoreboardUpdateType.ENDED);
                            }
                        }

                        return;
                    }

                    millis -= 100;

                    String ms = getTime(millis, true);

                    String newValue = startValue.replaceAll("%count%", ms);

                    setValue(newValue);
                }
                else
                {
                    if (upTime >= millis)
                    {
                        Bukkit.getScheduler().cancelTask(getTaskId());

                        if (getListeners() != null)
                        {
                            for (ScoreboardUpdateListener listener : getListeners())
                            {
                                listener.update((ScoreboardCountObject) getInstance(), ScoreboardUpdateType.ENDED);
                            }
                        }

                        return;
                    }

                    upTime += 100;

                    String ms = getTime(upTime, false);

                    String newValue = startValue.replaceAll("%count%", ms);

                    setValue(newValue);
                }

                if (getType().equals(ScoreboardObjectType.TITLE))
                {
                    getBoard().setTitle(getInstance());
                }

                if (getListeners() != null)
                {
                    for (ScoreboardUpdateListener listener : getListeners())
                    {
                        listener.update((ScoreboardCountObject) getInstance(), ScoreboardUpdateType.CHANGED);
                    }
                }
            }
        }, 0L, 2L).getTaskId());
    }

    /**
     * Get the count type
     *
     * @return The type of this counter
     */
    public ScoreboardCountType getCountType()
    {
        return countType;
    }

    /**
     * Change the amount of millis to a nice string
     *
     * @param millis The amount of millis
     * @param useMillis To add millis or not to the string
     *
     * @return The String containing a nice string
     */
    public String getTime(int millis, boolean useMillis)
    {
        String minutes = String.valueOf((millis / 1000) / 60);
        String seconds = String.valueOf((millis / 1000) % 60);
        String dot = String.valueOf(
                millis - (Integer.valueOf(minutes) * 1000 * 60) - (Integer.valueOf(seconds) * 1000));
        String millisTime = dot.substring(0, 1);

        if ((millis / 1000) / 60 < 10)
        {
            minutes = "0" + minutes;
        }

        if ((millis / 1000) % 60 < 10)
        {
            seconds = "0" + seconds;
        }

        if (minutes.equals("0") || minutes.equals("00"))
        {
            if (useMillis)
            {
                return seconds + "." + millisTime;
            }
            else
            {
                return seconds;
            }
        }
        else
        {
            if (useMillis)
            {
                return minutes + ":" + seconds + "." + millisTime;
            }
            else
            {
                return minutes + ":" + seconds;
            }
        }
    }

    /**
     * Resets the count back to its begin value
     */
    public void reset()
    {
        if (countType.equals(ScoreboardCountType.DOWN))
        {
            millis = beginMillis;
        }
        else
        {
            upTime = 0;
        }
    }
}
