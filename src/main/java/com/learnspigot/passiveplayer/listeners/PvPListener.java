package com.learnspigot.passiveplayer.listeners;

import com.learnspigot.passiveplayer.PassiveManager;
import com.learnspigot.smp.utils.chat.MessageUtil;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.Arrays;

@RequiredArgsConstructor
public class PvPListener implements Listener {

    private final PassiveManager passiveManager;

    @EventHandler
    public void onPlayerDamage(final EntityDamageByEntityEvent e) {
        cancelAndNotifyNoPvP(e, e.getDamager(), e.getEntity());
    }

    @EventHandler
    public void onProjectileHit(final ProjectileHitEvent e) {
        if(e.getEntity().getShooter() instanceof Entity en) {
            cancelAndNotifyNoPvP(e, en, e.getHitEntity());
        }
    }

    private void cancelAndNotifyNoPvP(final Cancellable cancellable, final Entity suspectedOffender, final Entity... entities) {
        if(entities.length > Arrays.stream(entities).filter(entity -> entity instanceof Player).filter(passiveManager::hasPvPEnabled).count()) {
            cancellable.setCancelled(true);
            MessageUtil.sendMessage(suspectedOffender, "<color:#990000>You cannot damage this player!");
        }
    }

}
