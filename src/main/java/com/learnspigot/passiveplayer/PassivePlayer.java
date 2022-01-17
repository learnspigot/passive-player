package com.learnspigot.passiveplayer;

import com.learnspigot.passiveplayer.commands.PassiveCommand;
import com.learnspigot.passiveplayer.listeners.JoinQuitListener;
import com.learnspigot.passiveplayer.listeners.PvPListener;
import com.learnspigot.smp.utils.commandframework.CommandFramework;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class PassivePlayer extends JavaPlugin {

    @Override
    public void onEnable() {

        val passiveManager = new PassiveManager();

        for(final Player p : Bukkit.getOnlinePlayers()) {
            passiveManager.addUUID(p.getUniqueId());
        }

        val pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinQuitListener(passiveManager),this);
        pluginManager.registerEvents(new PvPListener(passiveManager),this);

        val commandFramework = new CommandFramework(this);
        commandFramework.registerCommands(new PassiveCommand(passiveManager));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
