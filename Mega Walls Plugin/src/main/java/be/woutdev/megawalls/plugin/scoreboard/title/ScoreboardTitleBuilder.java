package be.woutdev.megawalls.plugin.scoreboard.title;

import be.woutdev.megawalls.plugin.scoreboard.UserScoreboard;
import be.woutdev.megawalls.plugin.scoreboard.listener.ScoreboardUpdateListener;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardCountType;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardObject;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardObjectBuilder;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardObjectType;

/**
 * Created by Wout on 16/04/2016.
 */
public class ScoreboardTitleBuilder extends ScoreboardObjectBuilder
{
    public ScoreboardTitleBuilder(UserScoreboard board)
    {
        super(board, ScoreboardObjectType.TITLE);
    }

    /**
     * Create the new ScoreboardObject wich you now can use to set the title with the previously given info.
     *
     * @return The ScoreboardObject
     */
    @Override
    public ScoreboardObject create()
    {
        ScoreboardObject object;

        if (isCount())
        {
            if (getCountType() == ScoreboardCountType.DOWN)
            {
                object = new ScoreboardCountdownTitle(getBoard(), getMillis(), getValues().get(0),
                                                      getListeners().toArray(
                                                              new ScoreboardUpdateListener[getListeners().size()]));
            }
            else
            {
                object = new ScoreboardCountupTitle(getBoard(), getMillis(), getValues().get(0), getListeners().toArray(
                        new ScoreboardUpdateListener[getListeners().size()]));
            }
        }
        else if (isDynamic())
        {
            object = new ScoreboardDynamicTitle(getBoard(), getTicks(), isRandom(),
                                                getValues().toArray(new String[getValues().size()]),
                                                getListeners().toArray(
                                                        new ScoreboardUpdateListener[getListeners().size()]));
        }
        else
        {
            object = new ScoreboardTitle(getBoard(), getValues().get(0));
        }

        if (getWatcher() != null)
        {
            object.setWatcher(getWatcher());
        }

        return object;
    }
}
