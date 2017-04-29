package be.woutdev.megawalls.plugin.scoreboard.listener;

import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardChangeableObject;

/**
 * Created by Wout on 24/04/2016.
 */
public class CountDoneListener implements ScoreboardUpdateListener
{
    @Override
    public void update(ScoreboardChangeableObject object, ScoreboardUpdateType type)
    {
        if (type.equals(ScoreboardUpdateType.ENDED))
        {
            object.getBoard().removeEntryById(object.getId());
        }
    }
}
