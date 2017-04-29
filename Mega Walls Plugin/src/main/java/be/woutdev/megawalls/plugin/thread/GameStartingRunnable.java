package be.woutdev.megawalls.plugin.thread;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.plugin.helper.ScoreboardHelper;
import be.woutdev.megawalls.plugin.model.BasicGame;
import be.woutdev.megawalls.plugin.model.BasicUser;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Wout on 21/08/2016.
 */
public class GameStartingRunnable extends BukkitRunnable
{
    private final BasicGame game;
    private final int countGoal;
    private int count;
    private boolean started;

    public GameStartingRunnable(BasicGame game)
    {
        this.game = game;
        this.countGoal = MWAPI.getConfig().getLobbyCountdown(game.getMap());
        this.count = 0;
        this.started = false;
    }

    @Override
    public void run()
    {
        if (!isStarted())
        {
            started = true;
        }

        if (count < countGoal)
        {
            count++;
        }
        else
        {
            if (game.getLobbyPlayers().size() >=
                MWAPI.getConfig().getMinTeamSize(game.getMap()) * game.getMap().getAvailableTeams().size())
            {
                game.start();
            }
            else
            {
                game.getLobbyPlayers().forEach(p ->
                                               {
                                                   p.getPlayer()
                                                    .sendMessage(ChatColor.RED +
                                                                 "Not enough players in lobby to start the game.");

                                                   ScoreboardHelper.setWaitingLobbyScoreboard((BasicUser) p);
                                               });
            }

            started = false;
            cancel();
        }
    }

    public int getCount()
    {
        return count;
    }

    public boolean isStarted()
    {
        return started;
    }

    public void setStarted(boolean started)
    {
        this.started = started;
    }

    public String getFormattedCount()
    {
        int secondsLeft = countGoal - count;

        return getTime(secondsLeft * 1000);
    }

    private String getTime(int millis)
    {
        String minutes = String.valueOf((millis / 1000) / 60);
        String seconds = String.valueOf((millis / 1000) % 60);

        if ((millis / 1000) / 60 < 10)
        {
            minutes = "0" + minutes;
        }

        if ((millis / 1000) % 60 < 10)
        {
            seconds = "0" + seconds;
        }

        if (Integer.valueOf(minutes) == 0 && Integer.valueOf(seconds) <= 60)
        {
            return seconds;
        }

        if (minutes.equals("0") || minutes.equals("00"))
        {
            return seconds;
        }
        else
        {
            return minutes + ":" + seconds;
        }
    }
}
