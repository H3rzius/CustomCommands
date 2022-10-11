package me.h3rzius.h3rcustomcommands;

import me.h3rzius.h3rcustomcommands.commands.*;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class H3rCustomCommands extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        getCommand("adios").setExecutor(new SCommand());
        getCommand("pvp").setExecutor(new PvPCommand());
        getCommand("troll").setExecutor(new TrollCommand());
        getCommand("h3r").setExecutor(new H3rCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        ConsoleCommandSender ccs = getServer().getConsoleSender();
        ccs.sendMessage("Plugin deshabilitado, puede que sea un problema al cargar el plugin");
        System.out.println("disabled plugin, check out the logs to inspect what fails to load the plugin");
    }

    public void configFile() {

    }

}
