package me.h3rzius.h3rcustomcommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;

import static org.bukkit.Bukkit.getLogger;

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
            if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
                p.sendMessage(config.getString("h3r-help"));
                return true;
            } else if (args[0].equalsIgnoreCase("reload")) {
                if (p.hasPermission("h3rcustomcommands.reload")) {
                    reloadPlugin();
                } else {
                    p.sendMessage(config.getString("no-permissions"));
                }
                return true;
            }
        } else {
            ConsoleCommandSender ccs = (ConsoleCommandSender) sender;
            if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
                ccs.sendMessage(config.getString("h3r-help"));
                return true;
            } else if (args[0].equalsIgnoreCase("reload")) {
                if (ccs.hasPermission("h3rcustomcommands.reload")) {
                    reloadPlugin();
                } else {
                    ccs.sendMessage(config.getString("no-permissions"));
                }
                return true;
            }
        }
        return true;
    }

    public void reloadPlugin() {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands");
        if (plugin != null) {
            Bukkit.getServer().getPluginManager().disablePlugin(plugin);
            Bukkit.getServer().getPluginManager().enablePlugin(plugin);
            getLogger().info(config.getString("reload"));
        } else {
            getLogger().warning(config.getString("reload-failed"));
        }
    }
}
