package be.woutdev.megawalls.plugin.scoreboard;


import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardObjective;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Wout on 17/04/2016.
 */
public class ObjectiveFactory
{
    private UserScoreboard board;
    private ConcurrentLinkedQueue<ScoreboardObjective> objectives;

    public ObjectiveFactory(UserScoreboard board)
    {
        this.board = board;
        this.objectives = new ConcurrentLinkedQueue<ScoreboardObjective>();
    }

    /**
     * Add the given ScoreboardObjective to the factory
     *
     * @param objective The ScoreboardObjective instance
     */
    public void add(ScoreboardObjective objective)
    {
        objectives.add(objective);
    }

    /**
     * Get and remove an ScoreboardObjective from the queue
     *
     * @return The ScoreboardObjective
     */
    public ScoreboardObjective get()
    {
        return objectives.poll();
    }

    /**
     * Add a new ScoreboardObjective to the queue
     */
    public void addOne()
    {
        new ScoreboardObjective(board);
    }

    /**
     * Cache a few ScoreboardObjectives
     */
    public void cache(int amount)
    {
        for (int i = 0; i < amount; i++)
        {
            addOne();
        }
    }
}
