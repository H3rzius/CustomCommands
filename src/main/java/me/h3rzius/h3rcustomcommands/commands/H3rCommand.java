package me.h3rzius.h3rcustomcommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class H3rCommand implements CommandExecutor {
    FileConfiguration config, reloadConfig;
    File file = new File(Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getDataFolder(), "config.yml");
    File[] files = {
            new File(Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getDataFolder(), "staffdata.yml"),
            new File(Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getDataFolder(), "config.yml")
    };
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        config = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getConfig();
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                p.sendMessage(config.getString("h3r-help"));
                return true;
            }
            if (args.length > 0) {
                p.sendMessage(config.getString("h3r-help"));
                if (args[0].equalsIgnoreCase("help")) {
                    p.sendMessage(config.getString("h3r-help"));
                    return true;
                } else if (args[0].equalsIgnoreCase("reload")) {
                    if (p.hasPermission("h3rcustomcommands.reload")) {
                        for (int i = 0; i < files.length; i++) {
                            config = YamlConfiguration.loadConfiguration(files[i]);
                        }
                        return true;
                    } else {
                        p.sendMessage(config.getString("no-permissions"));
                        return false;
                    }
                } else {
                    p.sendMessage(config.getString("h3r-help"));
                    return true;
                }
            }
        } else {
            ConsoleCommandSender ccs = (ConsoleCommandSender) sender;
            if (args.length == 0) {
                ccs.sendMessage(config.getString("h3r-help"));
                return true;
            }
            if (args.length > 0) {
                ccs.sendMessage(config.getString("h3r-help"));
                if (args[0].equalsIgnoreCase("help")) {
                    ccs.sendMessage(config.getString("h3r-help"));
                    return true;
                } else if (args[0].equalsIgnoreCase("reload")) {
                    for (int i = 0; i < files.length; i++) {
                        config = YamlConfiguration.loadConfiguration(files[i]);
                    }
                    return true;
                } else {
                    ccs.sendMessage(config.getString("h3r-help"));
                    return true;
                }
            }
        }
        return true;
    }
}
