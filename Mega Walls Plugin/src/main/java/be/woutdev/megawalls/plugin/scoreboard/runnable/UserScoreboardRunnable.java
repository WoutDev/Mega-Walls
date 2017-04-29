package be.woutdev.megawalls.plugin.scoreboard.runnable;

import be.woutdev.megawalls.plugin.scoreboard.UserScoreboard;
import be.woutdev.megawalls.plugin.scoreboard.objects.FakeOfflinePlayer;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardIndexableObject;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardObjective;
import com.google.common.collect.Lists;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Wout on 16/04/2016.
 */
public class UserScoreboardRunnable implements Runnable
{
    private final UserScoreboard scoreboard;
    private final List<ChatColor> colorsUsed;
    private final List<Integer> onBoard;
    private CopyOnWriteArraySet<ScoreboardIndexableObject> currentEntries;

    public UserScoreboardRunnable(UserScoreboard scoreboard)
    {
        this.scoreboard = scoreboard;
        this.colorsUsed = Lists.newArrayList();
        this.onBoard = Lists.newArrayList();
    }

    @Override
    public void run()
    {
        Player player = scoreboard.getPlayer();

        if (player == null)
        {
            scoreboard.stop();
            return;
        }

        currentEntries = new CopyOnWriteArraySet<ScoreboardIndexableObject>(scoreboard.getEntries());

        for (String s : scoreboard.getScoreboard().getEntries())
        {
            if (scoreboard.getEntryByValue(s) == null)
            {
                scoreboard.getScoreboard().resetScores(s);
            }
        }

        ScoreboardObjective scoreboardObjective = scoreboard.getObjective();

        if (scoreboardObjective == null)
        {
            scoreboard.stop();
            return;
        }

        Objective objective = scoreboardObjective.getObjective();

        for (ScoreboardIndexableObject object : getCurrentEntries())
        {
            if (getOnBoard().contains(object.getId()))
            {
                if (object.isChanged())
                {
                    scoreboard.getScoreboard().resetScores(object.getPreviousValue());
                }
            }
            else
            {
                getOnBoard().add(object.getId());
            }

            setScore(object, objective);
        }

        getCurrentEntries().clear();
        getColorsUsed().clear();
        scoreboardObjective.done();
    }

    public List<Integer> getOnBoard()
    {
        return onBoard;
    }

    public List<ChatColor> getColorsUsed()
    {
        return colorsUsed;
    }

    public Set<ScoreboardIndexableObject> getCurrentEntries()
    {
        return currentEntries;
    }

    private void setScore(ScoreboardIndexableObject object, Objective objective)
    {
        setScore(object, objective, (scoreboard.getEntries().size() + 1) - object.getIndex());
    }

    private void setScore(ScoreboardIndexableObject object, Objective objective, int index)
    {
        Team team = getTeam(object.getId(), object);

        team.addPlayer(new FakeOfflinePlayer(team.getDisplayName()));

        objective
                .getScore(team.getDisplayName())
                .setScore(index);
    }

    private Team getTeam(int id, ScoreboardIndexableObject object)
    {
        Team team;
        if (scoreboard.getScoreboard().getTeam(String.valueOf(id)) == null)
        {
            team = scoreboard.getScoreboard().registerNewTeam(String.valueOf(id));
        }
        else
        {
            team = scoreboard.getScoreboard().getTeam(String.valueOf(id));
        }

        String value = object.getValue();

        if (object.getWatcher() != null)
        {
            value = value.replaceAll("%v%", object.getWatcher().get(scoreboard).toString());
        }

        String prefix = "", entry = "", suffix = "";

        if (value.length() >= 31)
        {
            value = value.substring(0, 30);
        }

        ChatColor color = nextColor();

        if (value.length() <= 16)
        {
            prefix = value;
            suffix = "";
            entry = color.toString();
        }
        else
        {
            if (value.charAt(15) == '&' || value.charAt(15) == '§')
            {
                prefix = value.substring(0, 15);
                suffix = value.substring(15, value.length() >= 29 ? 29 : value.length());
            }
            else
            {
                prefix = value.substring(0, 16);
                suffix = value.substring(16, value.length());
            }

            entry = color.toString();
        }

        while (suffix.length() != (16 - ChatColor.getLastColors(prefix).length()))
        {
            suffix += " ";
        }

        team.setPrefix(prefix);
        team.setDisplayName(entry);
        team.setSuffix(ChatColor.getLastColors(prefix) + suffix);

        return team;
    }

    private ChatColor nextColor()
    {
        ChatColor color = ChatColor.values()[getColorsUsed().size()];

        getColorsUsed().add(color);

        return color;
    }
}
