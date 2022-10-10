package me.h3rzius.h3rcustomcommands.commands;

import com.sun.org.apache.xpath.internal.objects.XNumber;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args[1] == "top") {
                player.setHealth(0.0);
                player.sendMessage("F");
                player.getServer().broadcastMessage(player.getDisplayName() + ChatColor.GOLD + " descubrio el secreto del suicide top");
            } else if (args[1] == "time") {
                if (args[2] == null || args[2] == "") {
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
            System.out.println("Oye, consolas no se pueden quitarse la vida");
            return false;
        }
        return true;
    }
}