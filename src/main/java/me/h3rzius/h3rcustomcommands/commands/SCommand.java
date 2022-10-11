package me.h3rzius.h3rcustomcommands.commands;

import me.h3rzius.h3rcustomcommands.H3rCustomCommands;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SCommand implements CommandExecutor {
    H3rCustomCommands h3r = new H3rCustomCommands();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = h3r.getConfig();
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.setHealth(0.0);
                player.getServer().broadcastMessage(player.getDisplayName() + ChatColor.RED + " se ha quitado la vida :(");
                player.sendMessage("F");
            } else if (args[0] == "top") {
                player.setHealth(0.0);
                player.sendMessage("F");
                player.getServer().broadcastMessage(player.getDisplayName() + ChatColor.GOLD + " descubrio el secreto del suicide top");
            } else if (args[0] == "time") {
                if (args[1] == null || args[1] == "") {
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
                    int seg = Integer.parseInt(args[2]);
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
        } else {
            ConsoleCommandSender ccs = h3r.getServer().getConsoleSender();
            ccs.sendMessage(ChatColor.RED + config.getString("as-console"));
        }
        return true;
    }
}