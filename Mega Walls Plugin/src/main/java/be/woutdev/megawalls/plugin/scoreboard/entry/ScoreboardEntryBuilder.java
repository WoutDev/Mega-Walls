package be.woutdev.megawalls.plugin.scoreboard.entry;

import be.woutdev.megawalls.plugin.scoreboard.UserScoreboard;
import be.woutdev.megawalls.plugin.scoreboard.listener.CountDoneListener;
import be.woutdev.megawalls.plugin.scoreboard.listener.ScoreboardUpdateListener;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardCountType;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardIndexableObject;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardObjectBuilder;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardObjectType;

/**
 * Created by Wout on 16/04/2016.
 */
public class ScoreboardEntryBuilder extends ScoreboardObjectBuilder
{
    /**
     * Get a ScoreboardEntryBuilder
     *
     * @param board The TropicalScoreboard used to place the created entry on
     */
    public ScoreboardEntryBuilder(UserScoreboard board)
    {
        super(board, ScoreboardObjectType.ENTRY);
    }

    /**
     * Create a new ScoreboardObject for entries
     *
     * @return The ScoreboardObject created from the given values
     */
    @Override
    public ScoreboardIndexableObject create()
    {
        ScoreboardIndexableObject object;

        if (isCount())
        {
            withListener(new CountDoneListener());

            if (getCountType() == ScoreboardCountType.DOWN)
            {
                object = new ScoreboardCountdownEntry(getBoard(), getMillis(), getValues().get(0),
                                                      getListeners().toArray(
                                                              new ScoreboardUpdateListener[getListeners().size()]));
            }
            else
            {
                object = new ScoreboardCountupEntry(getBoard(), getMillis(), getValues().get(0), getListeners().toArray(
                        new ScoreboardUpdateListener[getListeners().size()]));
            }
        }
        else if (isDynamic())
        {
            object = new ScoreboardDynamicEntry(getBoard(), getTicks(), isRandom(), getValues().toArray(
                    new String[getValues().size()]), getListeners().toArray(
                    new ScoreboardUpdateListener[getListeners().size()]));
        }
        else
        {
            object = new ScoreboardEntry(getBoard(), getValues().get(0));
        }

        if (getWatcher() != null)
        {
            object.setWatcher(getWatcher());
        }

        return object;
    }
}
