package me.h3rzius.h3rcustomcommands;

import me.h3rzius.h3rcustomcommands.commands.*;
import me.h3rzius.h3rcustomcommands.events.BlockSpectatorTeleportEvent;
import me.h3rzius.h3rcustomcommands.files.LangFile;
import me.h3rzius.h3rcustomcommands.files.StaffDataFile;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class H3rCustomCommands extends JavaPlugin {

    private static H3rCustomCommands instance;
    private BlockSpectatorTeleportEvent bste = new BlockSpectatorTeleportEvent();

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        ConsoleCommandSender ccs = getServer().getConsoleSender();
        // Appears by enabling plugin in console (my minecraft skin)
        ccs.sendMessage("\n" +
                    "   ░░░░░░░░░░░░░░░░              " + "\n"
                 +  "   ░░░░░░░░░░░░░░░░              " + "\n"
                 +  "   ░░░░▒▒░░░░▒▒░░░░   H3rzius    " + "\n"
                 +  "   ░░▒▒▒▒▒▒▒▒▒▒▒▒░░   Custom     " + "\n"
                 +  "   ▒▒██░░▒▒▒▒░░██▒▒   Commands   " + "\n"
                 +  "   ▒▒██░░▒▒▒▒░░██▒▒              " + "\n"
                 +  "   ▒▒▒▒▒▒░░░░▒▒▒▒▒▒   enabled    " + "\n"
                 +  "   ▒▒▒▒░░░░░░░░▒▒▒▒              " + "\n");
        this.saveDefaultConfig();
        StaffDataFile.setup();
        StaffDataFile.get().options().copyDefaults();
        StaffDataFile.save();
        /*LangFile.setup();
        LangFile.get().options().copyDefaults();
        LangFile.save();*/
        getCommand("suicidetop").setExecutor(new SCommand());
        //getCommand("turnswitch").setExecutor(new SwitchCommand());
        getCommand("pvp").setExecutor(new PvPCommand());
        getCommand("troll").setExecutor(new TrollCommand());
        getCommand("h3r").setExecutor(new H3rCommand());
        getCommand("stafftools").setExecutor(new StaffCommand());
        //getCommand("hack").setExecutor(new HackCommand());
        //getCommand("survey").setExecutor(new SurveyCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        ConsoleCommandSender ccs = getServer().getConsoleSender();
        ccs.sendMessage("");
        System.out.println("disabled plugin, check out the logs to inspect what fails to load the plugin");
    }

    public static H3rCustomCommands getInstance() {
        return instance;
    }

}
