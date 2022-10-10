package me.h3rzius.h3rcustomcommands.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PvPCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("h3rcustomcommands.pvp")) {
                boolean pvpActivator = player.getLocation().getWorld().getPVP();
                if (pvpActivator) {
                    player.getLocation().getWorld().setPVP(false);
                    player.sendMessage(ChatColor.GREEN + "PvP Desactivado");
                } else {
                    player.getLocation().getWorld().setPVP(true);
                    player.sendMessage(ChatColor.RED + "PvP Activado");
                }
            } else {
                player.sendMessage("No tienes permisos para ejecutar este comando");
            }
        }
        return true;
    }
}
