package me.h3rzius.h3rcustomcommands.files;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LangFile {
    private static String fileName = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getConfig().getString("locale");
    private static File filePath = new File(Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getDataFolder(), "lang");
    private static File file = new File(filePath, fileName + ".yml");
    private static FileConfiguration langFile = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getConfig();//YamlConfiguration.loadConfiguration(file);

    public static FileConfiguration get() {
        return langFile;
    }
    public static void set(String path, String text) {

        //langFile.set(path, text);
    }
    public static void setup() {
        if(!filePath.exists()) {
            try {
                filePath.mkdir();
                if (!file.exists()) {
                    langFile = YamlConfiguration.loadConfiguration(file);
                    file.createNewFile();
                    /*langFile.set("no-permissions", "No tienes permisos para ejecutar este comando");
                    langFile.set("player-not-entered", "nunca ha entrado al servidor!");
                    langFile.set("as-console", "Solo jugadores pueden ejecutar el comando");
                    langFile.set("disable-plugin", "Plugin deshabilitado, puede que sea un problema al cargar el plugin");
                    langFile.set("reload", "El plugin ha sido recargado");
                    langFile.set("reload-failed", "El plugin no pudo recargar, no existe ese plugin");
                    langFile.set("command-disabled", "El comando que elegiste está desactivado");
                    langFile.set("h3r-help", "Plugin creado por H3rzius :>");
                    langFile.set("h3r-status", "Jugador: %player_name%");
                    langFile.set("h3r-easteregg", "H3rzius pasó por aquí");
                    langFile.set("adios-top", "<player> descubrio el secreto del suicide top");
                    langFile.set("adios-message", 'F');
                    langFile.set("no-command-to-switch", "Necesitas escribir el comando que quieras (des)activar.");
                    langFile.set("switched-successfully", "El comando <cmd> ha sido <switch>");
                    langFile.set("switch-activate", "activado");
                    langFile.set("switch-deactivate", "desactivado");
                    langFile.set("switch-cmd-unknown", ">desconocido<");
                    langFile.set("activate-pvp", "PvP Activado");
                    langFile.set("deactivate-pvp", "PvP Desactivado");
                    langFile.set("trolled", "Fuiste troleado xd");
                    langFile.set("trolled-yourself", "Te troleaste a ti mismo xd");
                    langFile.set("got-trolled", "Has troleado a <player>");
                    langFile.set("stafftools-activate", "Modo Staff se ha activado");
                    langFile.set("stafftools-deactivate", "Modo Staff se ha desactivado");
                    langFile.set("cant-save-data", "El archivo staffdata.yml no pudo guardarse:");
                    langFile.set("hack-cmd-help", "Usa /hack <jugador>");
                    langFile.set("time-ban", "60d");
                    langFile.set("time-banip", "7d");
                    langFile.set("reason-ban", "Uso de hacks o cliente modificado no permitido en el servidor");
                    langFile.set("reason-banip", "Tienes 7 días para apelar. Baneo original: 60 Días");
                    langFile.set("survey", "[SURVEY]");
                    langFile.set("survey-player", "players");
                    langFile.set ("survey-answer", "Answer with </survey yes> or </survey no>");
                    langFile.set("survey-yes", "says yes");
                    langFile.set("survey-no", "says no");
                    langFile.set("no-surveys", "There is no survey in progress.");*/
                }
            } catch (IOException e) {
                System.out.println("An exception has appeard:");
                e.printStackTrace();
            }
        }
        langFile = YamlConfiguration.loadConfiguration(file);
    }
    public static void reload() {
        langFile = YamlConfiguration.loadConfiguration(file);
    }

    public static void save() {
        try {
            langFile.save(file);
        } catch (IOException e) {
            ConsoleCommandSender ccs = Bukkit.getServer().getConsoleSender();
            ccs.sendMessage("can't save lang data");
            e.printStackTrace();
        }
    }


}
