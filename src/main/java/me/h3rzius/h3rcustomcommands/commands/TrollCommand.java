package me.h3rzius.h3rcustomcommands.commands;

import me.h3rzius.h3rcustomcommands.H3rCustomCommands;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TrollCommand implements CommandExecutor {
    //H3rCustomCommands h3r = new H3rCustomCommands();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("h3rcustomcommands.troll")) {
                if (args.length == 0) {
                    //player.getWorld().strikeLightning(player.getLocation());
                    player.getLocation().getWorld().playSound(player.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 50.0F, 1.0F);
                    player.setHealth(1.0);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,200,5));
                    player.sendMessage(ChatColor.BOLD + "Te troleaste a ti mismo xD");
                } else {
                    Player player2 = Bukkit.getPlayer(args[0]);
                    //player2.getWorld().strikeLightning(player.getLocation());
                    player2.getLocation().getWorld().playSound(player.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN, 50.0F, 1.0F);
                    player2.setHealth(1.0);
                    player2.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,200,5));
                    player2.sendMessage(ChatColor.BOLD + "Fuiste troleado xD");
                    player.sendMessage(ChatColor.GOLD + player2.getDisplayName() + "fue troleado por ti");
                }
            } else {
                player.sendMessage("No tienes permisos :c");
            }
        }
        return true;
    }
}
