package me.h3rzius.h3rcustomcommands.commands.subcommands;

import me.h3rzius.h3rcustomcommands.commands.SubCommand;
import me.h3rzius.h3rcustomcommands.files.LangFile;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import static org.bukkit.Bukkit.getLogger;

public class ReloadCommand extends SubCommand {
    FileConfiguration lang = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getConfig();//LangFile.get();
    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "reloads the plugin";
    }

    @Override
    public String getSyntax() {
        return "/h3r reload";
    }

    @Override
    public boolean perform(CommandSender sender, String[] args) {
        if (sender.hasPermission("h3rcustomcommands.reload")) {
            reloadPlugin();
        } else {
            sender.sendMessage(lang.getString("no-permissions"));
        }
        return true;
    }

    public void reloadPlugin() {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands");
        if (plugin != null) {
            Bukkit.getServer().getPluginManager().disablePlugin(plugin);
            Bukkit.getServer().getPluginManager().enablePlugin(plugin);
            LangFile.reload();
            getLogger().info(lang.getString("reload"));
        } else {
            getLogger().warning(lang.getString("reload-failed"));
        }
    }
}
