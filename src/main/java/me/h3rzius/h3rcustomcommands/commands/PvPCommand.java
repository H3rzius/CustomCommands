package me.h3rzius.h3rcustomcommands.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PvPCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            boolean pvpActivator = player.getLocation().getWorld().getPVP();
            if (pvpActivator) {
                player.getLocation().getWorld().setPVP(false);
            } else {
                player.getLocation().getWorld().setPVP(true);
            }
        }
        return true;
    }
}
