package be.woutdev.megawalls.plugin.scoreboard.objects;

import be.woutdev.megawalls.plugin.scoreboard.UserScoreboard;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Wout on 17/04/2016.
 */
public class ScoreboardObjective
{
    private final UserScoreboard board;
    private final Objective objective;

    public ScoreboardObjective(UserScoreboard board)
    {
        this.board = board;
        this.objective = board.getScoreboard()
                              .registerNewObjective(String.valueOf(ThreadLocalRandom.current().nextInt(0, 100000)),
                                                    "dummy");

        board.getFactory().add(this);
    }

    /**
     * Update the title and get the objective
     *
     * @return The Objective
     */
    public Objective getObjective()
    {
        String value = ChatColor.translateAlternateColorCodes('&', board.getTitle().getValue());

        if (board.getTitle().getWatcher() != null)
        {
            value = value.replaceAll("%v%", board.getTitle().getWatcher().get(board).toString());
        }

        objective.setDisplayName(value);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        return objective;
    }

    /**
     * Call when you are done with this objective
     */
    public void done()
    {
        board.getFactory().add(this);
    }
}
