package me.h3rzius.h3rcustomcommands.commands;

import me.h3rzius.h3rcustomcommands.H3rCustomCommands;
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

public class SurveyCommand implements CommandExecutor {

    private final Map<UUID, Boolean> surveyAnswers = new HashMap<>();
    private String surveyTitle;
    private int yesCount = 0;
    private int noCount = 0;
    private boolean surveyInProgress = false;
    FileConfiguration config = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("survey")) {
            if (args.length < 2) {
                sender.sendMessage("Usage: /survey <title> <question>");
                return true;
            }

            if (surveyInProgress) {
                sender.sendMessage("A survey is already in progress.");
                return true;
            }

            surveyTitle = args[0];
            String surveyQuestion = String.join(" ", args);

            Bukkit.broadcastMessage(
                      ChatColor.GREEN
                    + config.getString("survey")
                    + " "
                    + ChatColor.AQUA
                    + surveyQuestion
                    + ". "
                    + ChatColor.GREEN
                    + config.getString("survey-answer")
            );

            surveyInProgress = true;

            Bukkit.getScheduler().scheduleSyncDelayedTask(H3rCustomCommands.getInstance(), () -> {
                endSurvey();
                Bukkit.broadcastMessage(
                        ChatColor.GREEN + config.getString("survey")
                                + " " + yesCount + " " + config.getString("survey-player")
                                + " " + config.getString("survey")
                                + " | " + noCount + " " + config.getString("survey-player")
                                + " " + config.getString("survey")
                );
            }, 60 * 20);

            return true;
        } else if (cmd.getName().equalsIgnoreCase(config.getString("survey-cmd-1")) || cmd.getName().equalsIgnoreCase(config.getString("survey-cmd-1"))) {
            Player player = (Player) sender;

            if (!surveyInProgress) {
                player.sendMessage(config.getString("no-surveys"));
                return true;
            }

            boolean answer = cmd.getName().equalsIgnoreCase(config.getString("survey-cmd-1"));
            surveyAnswers.put(player.getUniqueId(), answer);

            if (answer) {
                yesCount++;
            } else {
                noCount++;
            }

            return true;
        }

        return false;
    }

    private void endSurvey() {
        surveyInProgress = false;
        surveyAnswers.clear();
        yesCount = 0;
        noCount = 0;
    }
}
