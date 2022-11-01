package me.h3rzius.h3rcustomcommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 * @Author H3rzius
 */

public class PvPCommand implements CommandExecutor {
    FileConfiguration config;

    /**
     * PvP-command to de-/activate pvp, a tool for staff members that doesn't have creative or
     * spectator mode and needs to watch middle on a fight, it prevents to accidentally enter
     * into combat mode in most of the Combatlog's plugins.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        config = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getConfig();
        if (config.getBoolean("enable-pvp-cmd")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                // Player needs permissions to execute command.
                if (player.hasPermission("h3rcustomcommands.pvp")) {
                    boolean pvpActivator = player.getLocation().getWorld().getPVP();
                    if (pvpActivator) {
                        //If PvP are activated, it will deactivate
                        player.getLocation().getWorld().setPVP(false);
                        player.sendMessage(ChatColor.GREEN + config.getString("deactivate-pvp"));
                    } else {
                        //else it will activate
                        player.getLocation().getWorld().setPVP(true);
                        player.sendMessage(ChatColor.RED + config.getString("activate-pvp"));
                    }
                } else {
                    //
                    player.sendMessage(config.getString("no-permissions"));
                }
            } else {
                ConsoleCommandSender ccs = Bukkit.getServer().getConsoleSender();
                ccs.sendMessage(ChatColor.RED + config.getString("as-console"));
            }
            return true;
        } else {
            return false;
        }
    }
}
