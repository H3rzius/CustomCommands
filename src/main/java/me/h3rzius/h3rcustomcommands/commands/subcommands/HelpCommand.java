package me.h3rzius.h3rcustomcommands.commands.subcommands;

import me.h3rzius.h3rcustomcommands.commands.SubCommand;
import me.h3rzius.h3rcustomcommands.files.LangFile;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class HelpCommand extends SubCommand {
    FileConfiguration lang = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getConfig();//LangFile.get();
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "help command";
    }

    @Override
    public String getSyntax() {
        return "/h3r help";
    }

    @Override
    public boolean perform(CommandSender sender, String[] args) {
        sender.sendMessage(lang.getString("h3r-help"));
        return true;
    }
}
