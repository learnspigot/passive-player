package com.learnspigot.passiveplayer;

import com.learnspigot.passiveplayer.commands.PassiveCommand;
import com.learnspigot.passiveplayer.listeners.JoinQuitListener;
import com.learnspigot.passiveplayer.listeners.PvPListener;
import com.learnspigot.smp.core.utils.commandframework.CommandFramework;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class PassivePlayer extends JavaPlugin {

    @Override
    public void onEnable() {

        val passiveManager = new PassiveManager();

        for(val p : Bukkit.getOnlinePlayers()) {
            passiveManager.addUUID(p.getUniqueId());
        }

        val pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new JoinQuitListener(passiveManager),this);
        pluginManager.registerEvents(new PvPListener(passiveManager),this);

        val commandFramework = new CommandFramework(this);
        commandFramework.registerCommands(new PassiveCommand(passiveManager));

    }

}
