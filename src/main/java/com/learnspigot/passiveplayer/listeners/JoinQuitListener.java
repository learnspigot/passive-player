package com.learnspigot.passiveplayer.listeners;

import com.learnspigot.passiveplayer.PassiveManager;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@RequiredArgsConstructor
public class JoinQuitListener implements Listener {

    private final PassiveManager passiveManager;

    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        passiveManager.addUUID(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onQuit(final PlayerQuitEvent e) {
        passiveManager.removeUUID(e.getPlayer().getUniqueId());
    }

}
