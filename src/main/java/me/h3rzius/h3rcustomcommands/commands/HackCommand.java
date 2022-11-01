package me.h3rzius.h3rcustomcommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class HackCommand implements CommandExecutor {
    FileConfiguration config;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        config = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getConfig();
        if (config.getBoolean("enable-hack-cmd")) {
            String timeBan = config.getString("time-ban");
            String timeBanIP = config.getString("time-banip");
            String reason = config.getString("reason-ban");
            String reasonIP = config.getString("reason-banip");
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + config.getString("hack-cmd-help"));
            } else {
                Player p = Bukkit.getPlayer(args[0]);
                String ip = p.getAddress().getHostString();
                Bukkit.dispatchCommand(sender, "ban -s " + p + " " + timeBan + " " + reason);
                Bukkit.dispatchCommand(sender, "ban-ip -s " + ip + " " + timeBanIP + " " + reasonIP);
            }
            return true;
        } else {
            return false;
        }
    }
}
