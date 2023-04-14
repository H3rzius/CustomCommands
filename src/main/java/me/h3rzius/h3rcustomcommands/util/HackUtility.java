package me.h3rzius.h3rcustomcommands.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Arrays;
import java.util.UUID;

public class HackUtility {
    FileConfiguration config = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getConfig();
    String reason;

    /**
     *
     * @param sender Source of the command
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return
     *
     */
    public boolean execute(CommandSender sender, String[] args) {
        if (args[0].equalsIgnoreCase("hack")) {
            if (config.getBoolean("enable-hack")) {
                if (args.length < 1) {
                    sender.sendMessage("Usage: /hack [-r <reason>] <player>");
                    return true;
                }

                boolean hasReason = false;
                String playerName = args[1];
                UUID playerUUID = null;
                OfflinePlayer player = Bukkit.getPlayerExact(playerName);
                reason = config.getString("reason-ban");

                if (args[2].equalsIgnoreCase("-r")) {
                    if (args.length < 4) {
                        sender.sendMessage("Usage: /hack <player> [-r <reason>]");
                        return true;
                    }

                    //hasReason = true;
                    reason = String.join(" ", Arrays.copyOfRange(args, 4, args.length));
                    playerName = args[1];
                } else {
                    playerName = args[1];
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
                sender.sendMessage(ChatColor.RED + config.getString("command-disabled"));
                return false;
            }
        }
        return true;
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
