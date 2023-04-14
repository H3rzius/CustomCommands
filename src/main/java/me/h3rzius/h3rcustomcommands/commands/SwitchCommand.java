package me.h3rzius.h3rcustomcommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
@Deprecated
public class SwitchCommand implements CommandExecutor {
    FileConfiguration config = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getConfig();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String cmd = "";
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length < 1) {
                    player.sendMessage(config.getString("no-command-to-switch"));
                    return true;
            }
            cmd = "enable-" + args[0];
            if (config.getBoolean(cmd)) {
                config.set(cmd, false);
                String response = config.getString("switched-successfully");
                response.replaceAll("<cmd>", args[0]);
                response.replaceAll("<switch>", config.getString("switch-deactivate"));
                player.sendMessage(ChatColor.GREEN + response);
            } else {
                config.set(cmd, true);
                String response = config.getString("switched-successfully");
                response.replaceAll("<cmd>", args[0]);
                response.replaceAll("<switch>", config.getString("switch-activate"));
                player.sendMessage(ChatColor.GREEN + response);
            }
            return true;
        } else if (sender instanceof ConsoleCommandSender) {
            ConsoleCommandSender ccs = (ConsoleCommandSender) sender;

            for (int i = 0; i < args.length; i++) {
                if (args.length < 1) {
                    ccs.sendMessage(config.getString("no-command-to-switch"));
                    return true;
                }
                cmd = "enable-" + args[0];
                if (config.getBoolean(cmd)) {
                    config.set(cmd, false);
                    String response = config.getString("switched-successfully");
                    response.replaceAll("<cmd>", args[0]);
                    response.replaceAll("<switch>", config.getString("switch-deactivate"));
                    ccs.sendMessage(ChatColor.GREEN + response);
                } else {
                    config.set(cmd, true);
                    String response = config.getString("switched-successfully");
                    response.replaceAll("<cmd>", args[0]);
                    response.replaceAll("<switch>", config.getString("switch-deactivate"));
                    ccs.sendMessage(ChatColor.GREEN + response);
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
