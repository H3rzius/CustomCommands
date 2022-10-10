package me.h3rzius.h3rcustomcommands;

import me.h3rzius.h3rcustomcommands.commands.SCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class H3rCustomCommands extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("adios").setExecutor(new SCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
