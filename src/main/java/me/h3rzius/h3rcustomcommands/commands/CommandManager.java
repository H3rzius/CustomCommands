package me.h3rzius.h3rcustomcommands.commands;

import me.h3rzius.h3rcustomcommands.commands.subcommands.ExplodeCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandManager implements CommandExecutor {

    private ArrayList<SubCommand> subCommands = new ArrayList<>();
    public CommandManager() {
        subCommands.add(new ExplodeCommand());
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {
            for (int i = 0; i < getSubCommands().size(); i++) {
                if (args[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
                    getSubCommands().get(i).perform(sender, args);
                }
            }
        }
        return true;
    }
    public ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }

}
