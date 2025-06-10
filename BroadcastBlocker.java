package com.yourname.broadcastblocker;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.BroadcastMessageEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Arrays;
import java.util.List;

public class BroadcastBlocker extends JavaPlugin implements Listener {

    private final List<String> blockedPatterns = Arrays.asList(
            "playit.gg",
            "finance-sorts.joinmc.link",
            "tunnel setup",
            "restarting connection as requested by rcon"
    );

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("BroadcastBlocker enabled!");
    }

    @EventHandler
    public void onBroadcast(BroadcastMessageEvent event) {
        String msg = event.message().toLowerCase();
        for (String pattern : blockedPatterns) {
            if (msg.contains(pattern)) {
                event.setCancelled(true);
                getLogger().info("Blocked a broadcast: " + msg);
                break;
            }
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String msg = event.getMessage().toLowerCase();
        for (String pattern : blockedPatterns) {
            if (msg.contains(pattern)) {
                event.setCancelled(true);
                break;
            }
        }
    }
}
