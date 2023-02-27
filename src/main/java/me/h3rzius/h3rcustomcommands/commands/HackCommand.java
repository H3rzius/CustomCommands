package me.h3rzius.h3rcustomcommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.UUID;

public class HackCommand implements CommandExecutor {
    FileConfiguration config;
    Player player;
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
        // Check that the sender has permission to ban other players
        if (!sender.hasPermission("h3rcustomcommands.hack")) {
            sender.sendMessage("You do not have permission to use this command!");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage("Usage: /hack <player> <reason>");
            return true;
        }
        player = Bukkit.getPlayer(args[0]);

        // Get the player that the command is targeting
        OfflinePlayer target = Bukkit.getOfflinePlayer(player.getUniqueId());

        // If the target player is online, execute the ban command as the player
        if (target.isOnline()) {
            Player onlineTarget = target.getPlayer();

            // Construct and execute the ban command as the player
            String banCommand = "/ban -s " + onlineTarget.getName() + " 60d " + args[1];
            onlineTarget.performCommand(banCommand);

            // Notify the sender that the ban command has been executed
            sender.sendMessage("Executed command: " + banCommand);

        } else {
            // If the target player is offline, execute the ban command as the console

            // Check that the sender has permission to ban offline players
            if (!sender.hasPermission("ban.offlineplayer")) {
                sender.sendMessage("You do not have permission to ban offline players!");
                return true;
            }

            // Check that the target player has played on the server before
            if (!target.hasPlayedBefore()) {
                sender.sendMessage(args[0] + " has never played on this server!");
                return true;
            }

            // Construct and execute the ban command as the console
            String banCommand = "/ban -s " + target.getName() + " 60d " + args[1];
            sender.getServer().dispatchCommand(sender.getServer().getConsoleSender(), banCommand);

            // Notify the sender that the ban command has been executed
            sender.sendMessage("Has ejecutado el comando: " + banCommand);
        }
        return true;
    }
}
