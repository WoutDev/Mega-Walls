package be.woutdev.megawalls.plugin.scoreboard;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.model.User;
import be.woutdev.megawalls.plugin.scoreboard.entry.ScoreboardEntry;
import be.woutdev.megawalls.plugin.scoreboard.entry.ScoreboardEntryBuilder;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardChangeableObject;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardIndexableObject;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardObject;
import be.woutdev.megawalls.plugin.scoreboard.objects.ScoreboardObjective;
import be.woutdev.megawalls.plugin.scoreboard.runnable.UserScoreboardRunnable;
import be.woutdev.megawalls.plugin.scoreboard.title.ScoreboardTitleBuilder;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Wout on 16/04/2016.
 */
public class UserScoreboard
{
    private final UserScoreboardRunnable scoreboardRunnable;
    private final Scoreboard scoreboard;
    private final ObjectiveFactory factory;
    private final User user;
    private ScoreboardObject title;
    private CopyOnWriteArraySet<ScoreboardIndexableObject> entries;
    private int taskId;
    private int blanks;
    private boolean isStarted;

    public UserScoreboard(User user)
    {
        this.user = user;
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        this.title = getTitleBuilder().onlyValue("&eMega Walls").create();
        this.factory = new ObjectiveFactory(this);
        this.entries = new CopyOnWriteArraySet<>();
        this.scoreboardRunnable = new UserScoreboardRunnable(this);
        this.blanks = 0;
        this.isStarted = false;

        this.factory.cache(3);
    }

    public ScoreboardEntryBuilder getEntryBuilder()
    {
        return new ScoreboardEntryBuilder(this);
    }

    public ScoreboardTitleBuilder getTitleBuilder()
    {
        return new ScoreboardTitleBuilder(this);
    }

    public void addEntry(ScoreboardObject entry)
    {
        ScoreboardIndexableObject newEntry = (ScoreboardIndexableObject) entry;
        newEntry.setBoard(this);
        newEntry.setIndex(entries.size() + 1);

        entries.add(newEntry);

        if (!isStarted)
        {
            start();
        }
        else
        {
            startChangeableObject(entry);
        }
    }

    public void addBlank()
    {
        ScoreboardEntry entry = (ScoreboardEntry) getEntryBuilder().onlyValue(StringUtils.repeat(" ", ++blanks))
                                                                   .create();

        addEntry(entry);
    }

    public void setEntry(int index, ScoreboardObject entry)
    {
        ScoreboardIndexableObject newEntry = (ScoreboardIndexableObject) entry;
        newEntry.setBoard(this);
        newEntry.setIndex(index);

        if (getEntry(index) == null)
        {
            entries.add(newEntry);
        }
        else
        {
            pauseChangeableObject(getEntry(index));

            entries.remove(getEntry(index));
            entries.add(newEntry);
        }

        if (!isStarted)
        {
            start();
        }
        else
        {
            startChangeableObject(entry);
        }
    }

    public void setBlank(int index)
    {
        ScoreboardEntry entry = (ScoreboardEntry) getEntryBuilder().onlyValue(StringUtils.repeat(" ", ++blanks))
                                                                   .create();

        setEntry(index, entry);
    }

    public ScoreboardIndexableObject getEntry(int index)
    {
        for (ScoreboardIndexableObject entry : entries)
        {
            if (entry.getIndex() == index)
            {
                return entry;
            }
        }

        return null;
    }

    public ScoreboardIndexableObject getEntryById(int id)
    {
        for (ScoreboardIndexableObject entry : entries)
        {
            if (entry.getId() == id)
            {
                return entry;
            }
        }

        return null;
    }

    public ScoreboardIndexableObject getEntryByValue(String value)
    {
        Team team = getTeamByValue(value);

        if (team == null)
        {
            return null;
        }

        for (ScoreboardIndexableObject entry : entries)
        {
            if (team.getName().equals(String.valueOf(entry.getId())))
            {
                return entry;
            }
        }

        return null;
    }

    public ScoreboardIndexableObject getEntryByValueSnippet(String snippet)
    {
        for (ScoreboardIndexableObject entry : entries)
        {
            if (entry.getValue().contains(snippet))
            {
                return entry;
            }
        }

        return null;
    }

    public void removeEntry(int index)
    {
        for (ScoreboardIndexableObject entry : entries)
        {
            if (entry.getIndex() == index)
            {
                entries.remove(entry);
            }
        }

        while (true)
        {
            index++;

            ScoreboardIndexableObject obj = getEntry(index);
            if (obj == null)
            {
                return;
            }

            obj.setIndex(obj.getIndex() - 1);
        }
    }

    public void removeEntryById(int id)
    {
        int index = -1;

        for (ScoreboardIndexableObject entry : entries)
        {
            if (entry.getId() == id)
            {
                entries.remove(entry);

                index = entry.getIndex();
                break;
            }
        }

        while (true)
        {
            index++;

            ScoreboardIndexableObject obj = getEntry(index);
            if (obj == null)
            {
                return;
            }

            obj.setIndex(obj.getIndex() - 1);
        }
    }

    public void start()
    {
        if (isStarted)
        {
            return;
        }

        startChangeableObject(title);

        for (ScoreboardIndexableObject object : entries)
        {
            startChangeableObject(object);
        }

        taskId = Bukkit.getScheduler()
                       .runTaskTimerAsynchronously(MWAPI.getPlugin(),
                                                   scoreboardRunnable, 0L, 2L)
                       .getTaskId();
        isStarted = true;

        getPlayer().setScoreboard(scoreboard);
    }

    public Player getPlayer()
    {
        return user.getPlayer();
    }

    public CopyOnWriteArraySet<ScoreboardIndexableObject> getEntries()
    {
        return entries;
    }

    public ScoreboardObject getTitle()
    {
        return title;
    }

    public void setTitle(ScoreboardObject title)
    {
        if (this.title.getId() != title.getId())
        {
            pauseChangeableObject(this.title);
        }

        this.title = title;

        if (!isStarted)
        {
            start();
        }
        else
        {
            startChangeableObject(title);
        }
    }

    public void clearEntries()
    {
        for (ScoreboardIndexableObject entry : entries)
        {
            removeEntryById(entry.getId());
        }

        /*scoreboardRunnable.getOnBoard().clear();

        for (String s : scoreboard.getEntries())
        {
            scoreboard.resetScores(s);
        }*/
    }

    public Scoreboard getScoreboard()
    {
        return scoreboard;
    }

    public ScoreboardObjective getObjective()
    {
        return factory.get();
    }

    public int getTaskId()
    {
        return taskId;
    }

    public void stop()
    {
        if (!isStarted)
        {
            return;
        }

        Bukkit.getScheduler().cancelTask(getTaskId());

        for (ScoreboardIndexableObject scoreboardObject : entries)
        {
            pauseChangeableObject(scoreboardObject);
        }

        pauseChangeableObject(title);

        isStarted = false;
    }

    public ObjectiveFactory getFactory()
    {
        return factory;
    }

    private Team getTeamByValue(String value)
    {
        for (Team team : scoreboard.getTeams())
        {
            if (team.getDisplayName().equals(value))
            {
                return team;
            }
        }

        return null;
    }

    private void pauseChangeableObject(ScoreboardObject object)
    {
        if (object instanceof ScoreboardChangeableObject)
        {
            ScoreboardChangeableObject changeableObject = (ScoreboardChangeableObject) object;

            if (changeableObject.getTaskId() != 0)
            {
                changeableObject.pause();
            }
        }
    }

    private void startChangeableObject(ScoreboardObject object)
    {
        if (object instanceof ScoreboardChangeableObject)
        {
            ScoreboardChangeableObject changeableObject = (ScoreboardChangeableObject) object;

            if (changeableObject.getTaskId() == 0)
            {
                changeableObject.start();
            }
        }
    }

    public User getUser()
    {
        return user;
    }
}
