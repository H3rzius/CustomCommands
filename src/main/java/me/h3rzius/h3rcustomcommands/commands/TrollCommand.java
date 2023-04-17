package me.h3rzius.h3rcustomcommands.commands;

import me.h3rzius.h3rcustomcommands.files.LangFile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TrollCommand implements CommandExecutor {
    FileConfiguration config;
    FileConfiguration lang = LangFile.get();

    /**
     * Troll command executes a surround sound,
     * loses health and gets confused for some seconds giving in config
     * @param sender
     * @param command
     * @param label
     * @param args
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        config = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getConfig();
        if (config.getBoolean("enable-troll")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                // Needs permissions to execute troll command
                if (player.hasPermission("h3rcustomcommands.troll")) {
                    // If user doesn't writes a nickname, he will troll himself.
                    if (args.length == 0) {
                        //player.getWorld().strikeLightning(player.getLocation());
                        player.getLocation().getWorld().playSound(player.getLocation(), Sound.WITHER_DEATH, 50.0F, 1.0F);
                        player.setHealth(1.0);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 5));
                        player.sendMessage(ChatColor.BOLD + "Te troleaste a ti mismo xD");
                    } else {
                        //else get first argument and set as a Player to proceed to troll
                        Player player2 = Bukkit.getPlayer(args[0]);

                        String gotTrolled = config.getString("got-trolled");
                        gotTrolled.replaceAll("<player>", player2.getDisplayName());
                        //player2.getWorld().strikeLightning(player.getLocation());
                        player2.getLocation().getWorld().playSound(player.getLocation(), Sound.WITHER_DEATH, 50.0F, 1.0F);
                        player2.setHealth(1.0);
                        player2.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 200, 5));
                        player2.sendMessage(ChatColor.BOLD + "Fuiste troleado xD");
                        player.sendMessage(ChatColor.GOLD + gotTrolled);
                    }
                } else {
                    ConsoleCommandSender ccs = Bukkit.getServer().getConsoleSender();
                    ccs.sendMessage(ChatColor.RED + config.getString("as-console"));
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
