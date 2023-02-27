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
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        config = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getConfig();
        if (sender instanceof Player) {
            Player p = (Player) sender;
            switch (args[0]) {
                case "reload":
                    reloadConfig = YamlConfiguration.loadConfiguration(file);
                case "status":
                    p.sendMessage(config.getString("h3r-status"));
                case "easteregg":
                    p.sendMessage(config.getString("h3r-easteregg"));
                case "master":
                    if(p.hasPermission("h3rcustomcommands.h3r.master")) {
                        p.sendMessage("Troll: " + config.getBoolean("enable-troll-cmd"));
                        p.sendMessage("Adios: " + config.getBoolean("enable-adios-cmd"));
                        p.sendMessage("PvP: " + config.getBoolean("enable-pvp-cmd"));
                        p.sendMessage("Hack: " + config.getBoolean("enable-hack-cmd"));
                    } else {
                        p.sendMessage(config.getString("no-permissions"));
                    }
            }

        } else {
            ConsoleCommandSender ccs = (ConsoleCommandSender) sender;
            ccs.sendMessage(config.getString("as-console"));
        }

        return true;
    }
}
