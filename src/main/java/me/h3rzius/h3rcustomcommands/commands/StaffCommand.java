package me.h3rzius.h3rcustomcommands.commands;

import me.h3rzius.h3rcustomcommands.files.StaffDataFile;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class StaffCommand implements CommandExecutor {

    File file;
    FileConfiguration config;
    StaffDataFile staffData;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getDataFolder(), "staffdata.yml");
        config = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getConfig();
        staffData.setup();
        if (sender instanceof Player) {
            Player p = (Player) sender;
            staffData.save();
            p.sendMessage(config.getString("stafftools"));
        } else {
            ConsoleCommandSender ccs = Bukkit.getServer().getConsoleSender();
            ccs.sendMessage(config.getString("stafftools"));
        }
        return true;
    }
}
