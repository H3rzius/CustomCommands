package me.h3rzius.h3rcustomcommands.commands;

import me.h3rzius.h3rcustomcommands.H3rCustomCommands;
import me.h3rzius.h3rcustomcommands.util.HackUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getLogger;

public class H3rCommand implements CommandExecutor, TabCompleter {
    FileConfiguration config, reloadConfig;
    File file = new File(Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getDataFolder(), "config.yml");
    File[] files = {
            new File(Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getDataFolder(), "staffdata.yml"),
            new File(Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getDataFolder(), "config.yml")
    };

    public H3rCommand() {
        PluginCommand command = H3rCustomCommands.getInstance().getCommand("h3r");
        if (command != null) {
            command.setExecutor(this);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        config = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getConfig();
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(config.getString("h3r-help"));
            return true;
        }
        String subCommand = args[0];
        switch (subCommand) {
            case "hack":
                HackUtility hack = new HackUtility();
                if(!hack.execute(sender, args)) {
                    sender.sendMessage(ChatColor.RED + config.getString("command-disabled"));
                }
                return true;
            case "enable":
                String cmdEnable = "enable-" + args[1];
                if (args.length < 2) {
                    sender.sendMessage(config.getString("no-command-to-switch"));
                    return true;
                }
                if (config.getBoolean(cmdEnable)) {
                    String response = config.getString("has-been-switched");
                    response.replaceAll("<cmd>", args[1]);
                    response.replaceAll("<switch>", config.getString("switch-activate"));
                    sender.sendMessage(ChatColor.GREEN + response);
                } else {
                    config.set(cmdEnable, true);
                    String response = config.getString("switched-successfully");
                    response.replaceAll("<cmd>", args[1]);
                    response.replaceAll("<switch>", config.getString("switch-activate"));
                    sender.sendMessage(ChatColor.GREEN + response);
                }
                return true;
            case "disable":
                String cmd = "enable-" + args[1];
                if (args.length < 1) {
                    sender.sendMessage(config.getString("no-command-to-switch"));
                    return true;
                }
                if (config.getBoolean(cmd)) {
                    config.set(cmd, false);
                    String response = config.getString("switched-successfully");
                    response.replaceAll("<cmd>", args[1]);
                    response.replaceAll("<switch>", config.getString("switch-deactivate"));
                    sender.sendMessage(ChatColor.GREEN + response);
                } else {
                    String response = config.getString("has-been-switched");
                    response.replaceAll("<cmd>", args[1]);
                    response.replaceAll("<switch>", config.getString("switch-deactivate"));
                    sender.sendMessage(ChatColor.GREEN + response);
                }
                return true;
            case "reload":
                if (sender.hasPermission("h3rcustomcommands.reload")) {
                    reloadPlugin();
                } else {
                    sender.sendMessage(config.getString("no-permissions"));
                }
                return true;
            case "help":
            default:
                sender.sendMessage(config.getString("h3r-help"));
                return true;
        }
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
