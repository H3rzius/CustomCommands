package me.h3rzius.h3rcustomcommands.commands;

import me.h3rzius.h3rcustomcommands.H3rCustomCommands;
import me.h3rzius.h3rcustomcommands.files.LangFile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Deprecated
public class SurveyCommand implements CommandExecutor {

    private final Map<UUID, Boolean> surveyAnswers = new HashMap<>();
    private String surveyTitle;
    private int yesCount = 0;
    private int noCount = 0;
    private boolean surveyInProgress = false;
    FileConfiguration config = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getConfig();
    FileConfiguration lang = LangFile.get();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("survey")) {
            if(config.getBoolean("enable-survey")) {
                if (args.length < 2) {
                    sender.sendMessage("Usage: /survey <title> <question>");
                    return true;
                }

                if (surveyInProgress) {
                    sender.sendMessage("A survey is already in progress.");
                    return true;
                }

                surveyTitle = args[0];
                String text = args[0];
                text = text.replace(surveyTitle, "");
                String surveyQuestion = String.join(" ", text);

                Bukkit.broadcastMessage(
                        ChatColor.GREEN
                                + lang.getString("survey")
                                + " "
                                + ChatColor.YELLOW
                                + surveyTitle
                                + ": "
                                + ChatColor.AQUA
                                + surveyQuestion
                                + ". "
                                + ChatColor.GREEN
                                + lang.getString("survey-answer")
                );

                surveyInProgress = true;

                Bukkit.getScheduler().scheduleSyncDelayedTask(H3rCustomCommands.getInstance(), () -> {
                    endSurvey();
                    Bukkit.broadcastMessage(
                            ChatColor.GREEN + config.getString("survey")
                                    + " " + yesCount + " " + lang.getString("survey-player")
                                    + " " + lang.getString("survey-yes")
                                    + " | " + noCount + " " + lang.getString("survey-player")
                                    + " " + lang.getString("survey-no")
                    );
                }, 60 * 20);
                if (args[0].equalsIgnoreCase("yes") || args[0].equalsIgnoreCase("no")) {
                    Player player = (Player) sender;

                    if (!surveyInProgress) {
                        player.sendMessage(lang.getString("no-surveys"));
                        return true;
                    }

                    boolean answer = args[0].equalsIgnoreCase("yes");
                    surveyAnswers.put(player.getUniqueId(), answer);

                    if (answer) {
                        yesCount++;
                    } else {
                        noCount++;
                    }

                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.RED + lang.getString("command-disabled"));
            }
        }
        return true;
    }

    private void endSurvey() {
        surveyInProgress = false;
        surveyAnswers.clear();
        yesCount = 0;
        noCount = 0;
    }
}
