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

    /**
     *
     * @param sender Source of the command
     * @param command Command which was executed
     * @param s Alias of the command which was used
     * @param args Passed command arguments
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        config = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getConfig();
        /** Check if the cmd is enabled in config.yml. Else the cmd disabled until enabling. */
        if (config.getBoolean("enable-hack-cmd")) {
            String timeBan = config.getString("time-ban");
            String timeBanIP = config.getString("time-banip");
            String reason = config.getString("reason-ban");
            String reasonIP = config.getString("reason-banip");
            /** Check if player or console has permissions to execute this cmd */
            if (!sender.hasPermission("h3rcustomcommands.hack") || !(sender instanceof ConsoleCommandSender)) {
                sender.sendMessage(config.getString("no-permissions"));
                return false;
            } else {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + config.getString("hack-cmd-help"));
                    return false;
                }
                if (args.length > 0) {
                    Player p = Bukkit.getPlayer(args[0]);
                    String ip = p.getAddress().getHostString();
                    Bukkit.dispatchCommand(sender, "ban -s " + p + " " + timeBan + " " + reason);
                    Bukkit.dispatchCommand(sender, "ban-ip -s " + ip + " " + timeBanIP + " " + reasonIP);
                    return true;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
