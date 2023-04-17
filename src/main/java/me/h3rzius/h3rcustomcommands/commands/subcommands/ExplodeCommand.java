package me.h3rzius.h3rcustomcommands.commands.subcommands;

import me.h3rzius.h3rcustomcommands.commands.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ExplodeCommand extends SubCommand {
    @Override
    public String getName() {
        return "explode";
    }

    @Override
    public String getDescription() {
        return "standart description";
    }

    @Override
    public String getSyntax() {
        return "/h3r explode <nick>";
    }

    @Override
    public boolean perform(CommandSender sender, String[] args) {
        if (args.length > 1) {
            Player target = Bukkit.getPlayer(args[1]);
            sender.sendMessage(ChatColor.AQUA + "KABOOM");
            target.playSound(target.getLocation(), Sound.EXPLODE, 1,1);
            target.setHealth(0.0);
            target.sendMessage(ChatColor.DARK_RED + "KABOOM");
        } else if(args.length == 1) {
            sender.sendMessage("Player? --> /h3r explode <nick>");
        }
        return true;
    }
}
