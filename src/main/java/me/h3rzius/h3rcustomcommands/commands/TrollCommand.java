package me.h3rzius.h3rcustomcommands.commands;

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

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args[1] == null) {
                player.getWorld().strikeLightning(player.getLocation());
                player.getLocation().getWorld().playSound(player.getLocation(), Sound.WITHER_DEATH, 5.0F, 0.833F);
                player.setHealth(0.5);
                player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,10,5));
                player.sendMessage(ChatColor.BOLD + "Te troleaste a ti mismo xD");
            } else {
                Player player2 = Bukkit.getPlayer(args[1]);
                player2.getWorld().strikeLightning(player.getLocation());
                player2.getLocation().getWorld().playSound(player.getLocation(), Sound.WITHER_DEATH, 5.0F, 0.833F);
                player2.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION,10,5));
                player2.sendMessage(ChatColor.BOLD + "Fuiste troleado xD");
                player.sendMessage(ChatColor.GOLD + player2.getDisplayName() + "fue troleado por ti");
            }
        }
        return true;
    }
}
