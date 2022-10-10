package me.h3rzius.h3rcustomcommands;

import me.h3rzius.h3rcustomcommands.commands.PvPCommand;
import me.h3rzius.h3rcustomcommands.commands.SCommand;
import me.h3rzius.h3rcustomcommands.commands.TrollCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class H3rCustomCommands extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("adios").setExecutor(new SCommand());
        getCommand("pvp").setExecutor(new PvPCommand());
        getCommand("troll").setExecutor(new TrollCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
