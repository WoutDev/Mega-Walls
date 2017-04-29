package be.woutdev.megawalls.plugin.helper;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.plugin.model.BasicGame;
import be.woutdev.megawalls.plugin.model.BasicUser;
import be.woutdev.megawalls.plugin.scoreboard.UserScoreboard;
import org.bukkit.Bukkit;

import java.util.Arrays;

/**
 * Created by Wout on 21/08/2016.
 */
public class ScoreboardHelper
{
    public static void setWaitingLobbyScoreboard(BasicUser user)
    {
        if (user.getScoreboard() != null)
        {
            user.getScoreboard().stop();
        }

        Bukkit.getServer().getScheduler().runTask(MWAPI.getPlugin(), () ->
        {
            UserScoreboard scoreboard = new UserScoreboard(user);
            user.setScoreboard(scoreboard);

            scoreboard.addEntry(
                    scoreboard.getEntryBuilder().onlyValue("&6Map: &b" + user.getGame().getMap().getName()).create());
            scoreboard.addBlank();
            scoreboard.addEntry(scoreboard.getEntryBuilder()
                                          .values(Arrays.asList("Waiting", "Waiting.", "Waiting..", "Waiting..."))
                                          .dynamic(true)
                                          .ticks(20)
                                          .create());
            scoreboard.addBlank();
            scoreboard.addEntry(scoreboard.getEntryBuilder().onlyValue("&6Players: &b%v%").withValueWatcher(
                    scoreboard1 -> ((BasicGame) scoreboard1.getUser().getGame()).getLobbyPlayers().size()).create());
            scoreboard.addEntry(scoreboard.getEntryBuilder()
                                          .onlyValue("&6Selected Kit: &b%v%")
                                          .withValueWatcher(scoreboard1 -> "None")
                                          .create());
        });
    }

    public static void setCountdownLobbyScoreboard(BasicUser user)
    {
        if (user.getScoreboard() != null)
        {
            user.getScoreboard().stop();
        }

        Bukkit.getServer().getScheduler().runTask(MWAPI.getPlugin(), () ->
        {
            UserScoreboard scoreboard = new UserScoreboard(user);
            user.setScoreboard(scoreboard);

            scoreboard.addEntry(
                    scoreboard.getEntryBuilder().onlyValue("&6Map: &b" + user.getGame().getMap().getName()).create());
            scoreboard.addBlank();
            scoreboard.addEntry(scoreboard.getEntryBuilder()
                                          .onlyValue("&6Starting in: &b%v%")
                                          .withValueWatcher(scoreboard1 -> ((BasicGame) scoreboard1.getUser()
                                                                                                   .getGame()).getGameStartingRunnable()
                                                                                                              .getFormattedCount())
                                          .create());
            scoreboard.addBlank();
            scoreboard.addEntry(scoreboard.getEntryBuilder().onlyValue("&6Players: &b%v%").withValueWatcher(
                    scoreboard1 -> ((BasicGame) scoreboard1.getUser().getGame()).getLobbyPlayers().size()).create());
            scoreboard.addEntry(scoreboard.getEntryBuilder()
                                          .onlyValue("&6Selected Kit: &b%v%")
                                          .withValueWatcher(scoreboard1 -> "None")
                                          .create());
        });
    }
}
