package me.h3rzius.h3rcustomcommands.files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class StaffDataFile {

    private static File file;
    private static FileConfiguration data;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getDataFolder(), "staffdata.yml");

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("An exception has appeard:");
                e.printStackTrace();
            }
        }
        data = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return data;
    }

    public static void save() {
        try {
            data.save(file);
        } catch (IOException e) {
            System.out.println("new data couldn't be saved");
            e.printStackTrace();
        }
    }

    public static void reload() {
        data = YamlConfiguration.loadConfiguration(file);
    }

}
