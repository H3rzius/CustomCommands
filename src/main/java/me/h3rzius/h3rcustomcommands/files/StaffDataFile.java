package me.h3rzius.h3rcustomcommands.files;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class StaffDataFile {

    private static File file = new File(Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getDataFolder(), "staffdata.yml");
    private static FileConfiguration data;

    public static void setup() {
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
        FileConfiguration config = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getConfig();
        try {
            data.save(file);
        } catch (IOException e) {
            ConsoleCommandSender ccs = Bukkit.getServer().getConsoleSender();
            ccs.sendMessage(config.getString("cant-save-data"));
            e.printStackTrace();
        }
    }

    public static void reload() {
        data = YamlConfiguration.loadConfiguration(file);
    }

    public static void set(String path, String text) {
        data = YamlConfiguration.loadConfiguration(file);
        data.set(path, text);
    }
    public static void setItemStack(String toString, ItemStack item) {
        data = YamlConfiguration.loadConfiguration(file);
        data.set(toString, item);
    }

    public ItemStack getItemStack(String keys) {
        data = YamlConfiguration.loadConfiguration(file);
        return data.getItemStack(keys);
    }

    public Set<String> getKeys(boolean b) {
        data = YamlConfiguration.loadConfiguration(file);
        return data.getKeys(b);
    }
}
