package me.h3rzius.h3rcustomcommands.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class H3rCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                p.sendMessage("Plugin creado por H3rzius :>");
            } else if (args[0] == "help") {
                p.sendMessage("Plugin creado por H3rzius :>");
            } else if (args[0] == "reload") {
                p.sendMessage("Proximamente");
            } else {
                p.sendMessage("Plugin creado por H3rzius :>");
            }
        } else if (sender instanceof ConsoleCommandSender) {
            ConsoleCommandSender ccs = (ConsoleCommandSender) sender;
            if (args.length == 0) {
                ccs.sendMessage("Plugin creado por H3rzius :>");
            } else if (args[0] == "help") {
                ccs.sendMessage("Plugin creado por H3rzius :>");
            }
        }

        return true;
    }
}
