package me.h3rzius.h3rcustomcommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SCommand implements CommandExecutor {
    FileConfiguration config;

    /**
     *
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        config = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getConfig();
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.setHealth(0.0);
                player.getServer().broadcastMessage(player.getDisplayName() + ChatColor.RED + " se ha quitado la vida :(");
                player.sendMessage("F");
            } else {
                String subCommand = args[0];
                if (subCommand == "top") {
                    player.setHealth(0.0);
                    player.sendMessage("F");
                    String adiosTop = config.getString("adios-top");
                    adiosTop = adiosTop.replaceAll("<player>", player.getDisplayName());
                    player.getServer().broadcastMessage(ChatColor.GOLD + adiosTop);
                } else if (subCommand == "time") {
                    String numberString = args[1];
                    if (numberString == null || numberString == "") {
                        player.sendMessage("Procedes a quitarte la vida en 10 segundos");
                        try {
                            Thread.sleep(10000);
                            player.setHealth(0.0);
                            player.getServer().broadcastMessage(player.getDisplayName() + ChatColor.RED + " se ha quitado la vida :(");
                            player.sendMessage("F");
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        int seg = Integer.parseInt(numberString);
                        player.sendMessage("Procedes a quitarte la vida en " + seg + " segundo(s)");
                        seg = seg * 1000;
                        try {
                            Thread.sleep(seg);
                            player.setHealth(0.0);
                            player.getServer().broadcastMessage(player.getDisplayName() + ChatColor.RED + " se ha quitado la vida :(");
                            player.sendMessage("F");
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    player.setHealth(0.0);
                    player.getServer().broadcastMessage(player.getDisplayName() + ChatColor.RED + " se ha quitado la vida :(");
                    player.sendMessage("F");
                }
            }
        } else {
            ConsoleCommandSender ccs = Bukkit.getServer().getConsoleSender();
            ccs.sendMessage(ChatColor.RED + config.getString("as-console"));
        }
        return true;
    }
}