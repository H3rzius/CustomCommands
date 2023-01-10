package me.h3rzius.h3rcustomcommands.commands;

import me.h3rzius.h3rcustomcommands.files.StaffDataFile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;

public class StaffCommand implements CommandExecutor {

    File file;
    FileConfiguration config;
    StaffDataFile staffData;

    boolean isCommandActivated = false;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getDataFolder(), "staffdata.yml");
        config = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getConfig();
        staffData.setup();
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!isCommandActivated) {
                staffData.set("player", p.getUniqueId().toString());
                for(int i = 0 ; i < p.getInventory().getSize() ; i++) {
                    if(p.getInventory().getItem(i) != null) {
                        staffData.setItemStack("player.inventory." + i, p.getInventory().getItem(i));
                    }
                }
                staffData.save();
                p.sendMessage(ChatColor.GREEN + config.getString("stafftools-activate"));
                isCommandActivated = true;
            } else {
                for(String keys : staffData.getKeys(false)) {
                    int slot = Integer.parseInt(keys);
                    ItemStack item = staffData.getItemStack(keys);

                    p.getInventory().setItem(slot, item);
                }

                staffData.save();
                p.sendMessage(ChatColor.RED + config.getString("stafftools-deactivate"));
                isCommandActivated = false;
            }
            staffData.reload();
        } else {
            ConsoleCommandSender ccs = Bukkit.getServer().getConsoleSender();
            ccs.sendMessage(config.getString("as-console"));
        }
        return true;
    }
}
