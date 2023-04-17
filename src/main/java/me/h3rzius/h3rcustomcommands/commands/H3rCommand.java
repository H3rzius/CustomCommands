package me.h3rzius.h3rcustomcommands.commands;

import me.h3rzius.h3rcustomcommands.H3rCustomCommands;
import me.h3rzius.h3rcustomcommands.commands.subcommands.*;
import me.h3rzius.h3rcustomcommands.commands.subcommands.SurveyCommand;
import me.h3rzius.h3rcustomcommands.files.LangFile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getLogger;

public class H3rCommand implements CommandExecutor, TabCompleter {
    private ArrayList<SubCommand> subCommands = new ArrayList<>();
    FileConfiguration config;
    FileConfiguration lang = LangFile.get();
    private final Plugin PLUGIN = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands");

    public H3rCommand() {
        PluginCommand command = H3rCustomCommands.getInstance().getCommand("h3r");
        if (command != null) {
            command.setExecutor(this);
        }
        subCommands.add(new ReloadCommand());
        subCommands.add(new HackCommand());
        subCommands.add(new HelpCommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        config = PLUGIN.getConfig();
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(config.getString("h3r-help"));
            return true;
        }
        if(args.length > 1) {
            for (int i = 0; i < getSubCommands().size(); i++) {
                if (args[0].equalsIgnoreCase(getSubCommands().get(i).getName())) {
                    boolean thread = getSubCommands().get(i).perform(sender, args);
                    if (!thread) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }
        return true;
    }

    public ArrayList<SubCommand> getSubCommands() {
        return subCommands;
    }

    public void reloadPlugin() {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands");
        if (plugin != null) {
            Bukkit.getServer().getPluginManager().disablePlugin(plugin);
            Bukkit.getServer().getPluginManager().enablePlugin(plugin);
            getLogger().info(config.getString("reload"));
        } else {
            getLogger().warning(config.getString("reload-failed"));
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> strings = new ArrayList<>();
        return strings;
    }
}
