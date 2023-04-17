package me.h3rzius.h3rcustomcommands.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerTeleportEvent;

public class BlockSpectatorTeleportEvent {
    private FileConfiguration config = Bukkit.getServer().getPluginManager().getPlugin("H3rCustomCommands").getConfig();

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        if (event.getCause().equals(PlayerTeleportEvent.TeleportCause.SPECTATE)) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + config.getString("prevent-spectator-teleport"));
        }

    }
}
