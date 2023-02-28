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

import java.util.Arrays;
import java.util.UUID;

public class HackCommand implements CommandExecutor {
    FileConfiguration config = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getConfig();
    String reason;

    /**
     *
     * @param sender Source of the command
     * @param cmd Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return
     *
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("hack")) {
            if (config.getBoolean("enable-hack-cmd")) {
                if (args.length < 1) {
                    sender.sendMessage("Usage: /hack [-r <reason>] <player>");
                    return true;
                }

                boolean hasReason = false;
                String playerName = args[0];
                UUID playerUUID = null;
                OfflinePlayer player = Bukkit.getPlayerExact(playerName);
                reason = config.getString("reason-ban");

                if (args[0].equalsIgnoreCase("-r")) {
                    if (args.length < 3) {
                        sender.sendMessage("Usage: /hack [-r <reason>] <player>");
                        return true;
                    }

                    //hasReason = true;
                    reason = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
                    playerName = args[1];
                } else {
                    playerName = args[0];
                }

                if (player != null) {
                    playerUUID = player.getUniqueId();
                } else {
                    playerUUID = getOfflinePlayerUUID(playerName);
                }

                if (args.length >= 2) {
                    String ipReason = config.getString("reason-banip");
                    reason = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                    // execute command as op
                    if (playerUUID != null) {
                        String command = "/ban -s " + playerUUID + " 60d " + reason;
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                        String ipCommand = "/ban-ip -s " + playerUUID + " 7d " + reason + "... " + ipReason;
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ipCommand);
                        sender.sendMessage("Player banned successfully.");
                    } else {
                        sender.sendMessage("Player not found.");
                    }
                } else {
                    sender.sendMessage("Usage: /hack [-r <reason>] <player>");
                }
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private UUID getOfflinePlayerUUID(String playerName) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(playerName);
        if (player.hasPlayedBefore()) {
            return player.getUniqueId();
        } else {
            return null;
        }
    }
}
