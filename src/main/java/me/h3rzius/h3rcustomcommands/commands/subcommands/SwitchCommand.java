package me.h3rzius.h3rcustomcommands.commands.subcommands;

import me.h3rzius.h3rcustomcommands.commands.SubCommand;
import me.h3rzius.h3rcustomcommands.files.LangFile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SwitchCommand extends SubCommand {
    private FileConfiguration config = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getConfig();
    private FileConfiguration lang = LangFile.get();
    private final Plugin PLUGIN = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands");

    @Override
    public String getName() {
        return "switch";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getSyntax() {
        return null;
    }

    @Override
    public boolean perform(CommandSender sender, String[] args) {
        if (args.length == 1) {
            sender.sendMessage(config.getString("no-command-to-switch"));
            return true;
        }
        String cmd = "enable-" + args[1];
        if (config.getBoolean(cmd)) {
            config.set(cmd, false);
            config.options().copyDefaults(true);
            PLUGIN.saveConfig();
            PLUGIN.reloadConfig();
            String response = config.getString("switched-successfully");
            String newResponse = response.replaceAll("<cmd>", args[1]).replaceAll("<switch>", config.getString("switch-deactivate"));
            sender.sendMessage(ChatColor.RED + newResponse);
        } else {
            config.set(cmd, true);
            config.options().copyDefaults(true);
            PLUGIN.saveConfig();
            PLUGIN.reloadConfig();
            String response = config.getString("switched-successfully");
            String newResponse = response.replaceAll("<cmd>", args[1]).replaceAll("<switch>", config.getString("switch-activate"));
            sender.sendMessage(ChatColor.GREEN + newResponse);
        }
        return true;
    }
}
