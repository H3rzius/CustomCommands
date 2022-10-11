package me.h3rzius.h3rcustomcommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class H3rCommand implements CommandExecutor {
    FileConfiguration config;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        config = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getConfig();
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                p.sendMessage(config.getString("h3r-help"));
            } else {
                if (args[0] == "help") {
                    p.sendMessage(config.getString("h3r-help"));
                } else if (args[0] == "reload") {
                    p.sendMessage("Proximamente");
                } else {
                    p.sendMessage(config.getString("h3r-help"));
                }
            }
        } else if (sender instanceof ConsoleCommandSender) {
            ConsoleCommandSender ccs = (ConsoleCommandSender) sender;
            if (args.length == 0) {
                ccs.sendMessage(config.getString("h3r-help"));
            } else if (args[0] == "help") {
                ccs.sendMessage(config.getString("h3r-help"));
            }
        } else {
            System.out.println(":>");
        }

        return true;
    }
}
