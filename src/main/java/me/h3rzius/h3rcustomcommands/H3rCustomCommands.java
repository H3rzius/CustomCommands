package me.h3rzius.h3rcustomcommands;

import me.h3rzius.h3rcustomcommands.commands.*;
import me.h3rzius.h3rcustomcommands.files.StaffDataFile;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class H3rCustomCommands extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        ConsoleCommandSender ccs = getServer().getConsoleSender();
        // Appears by enabling plugin in console (my minecraft skin)
        ccs.sendMessage
                 ("   ░░░░░░░░░░░░░░░░              " + "\n"
                 +"   ░░░░░░░░░░░░░░░░              " + "\n"
                 +"   ░░░░▒▒░░░░▒▒░░░░   H3rzius    " + "\n"
                 +"   ░░▒▒▒▒▒▒▒▒▒▒▒▒░░   Custom     " + "\n"
                 +"   ▒▒██░░▒▒▒▒░░██▒▒   Commands   " + "\n"
                 +"   ▒▒██░░▒▒▒▒░░██▒▒              " + "\n"
                 +"   ▒▒▒▒▒▒░░░░▒▒▒▒▒▒   enabled    " + "\n"
                 +"   ▒▒▒▒░░░░░░░░▒▒▒▒              " + "\n");
        this.saveDefaultConfig();
        StaffDataFile.setup();
        StaffDataFile.get().options().copyDefaults();
        StaffDataFile.save();
        getCommand("suicidetop").setExecutor(new SCommand());
        getCommand("pvp").setExecutor(new PvPCommand());
        getCommand("troll").setExecutor(new TrollCommand());
        getCommand("h3r").setExecutor(new H3rCommand());
        getCommand("stafftools").setExecutor(new StaffCommand());
        getCommand("hack").setExecutor(new HackCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        ConsoleCommandSender ccs = getServer().getConsoleSender();
        ccs.sendMessage("");
        System.out.println("disabled plugin, check out the logs to inspect what fails to load the plugin");
    }

}
