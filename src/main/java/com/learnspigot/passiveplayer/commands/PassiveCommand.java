package com.learnspigot.passiveplayer.commands;

import com.learnspigot.passiveplayer.PassiveManager;
import com.learnspigot.smp.utils.chat.MessageUtil;
import com.learnspigot.smp.utils.commandframework.Command;
import com.learnspigot.smp.utils.commandframework.CommandArgs;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class PassiveCommand {

    private static final int TOGGLE_DELAY = 15;

    private final PassiveManager passiveManager;

    @Command(name = "passive", aliases = {"p", "pvp"}, inGameOnly = true, description = "Toggle PvP")
    public void passiveCommand(final CommandArgs args) {

        val player = args.getPlayer();

        final Player target;

        if(args.length() > 0 && player.hasPermission("learnspigot.passive.others")) {
            target = Bukkit.getPlayer(args.getArgs(0));

            if(target == null) {
                MessageUtil.sendMessage(player, "<red>Could not find a player with the name '" + args.getArgs(0) + "'");
                return;
            }

        } else {
            target = player;
        }

        val pvPData = passiveManager.getPvPData(target.getUniqueId());

        if(pvPData != null) {

            val secondsSinceLastToggle = pvPData.secondSinceLastToggle();

            if(secondsSinceLastToggle < TOGGLE_DELAY) {
                MessageUtil.sendMessage(player,"<red>You can not toggle PvP for another " + (15-secondsSinceLastToggle) + " seconds!");
                return;
            }

            if(pvPData.togglePvP()) {
                MessageUtil.sendMessage(player, "<red>PvP is now enabled!");
            } else {
                MessageUtil.sendMessage(player, "<green>PvP is now disabled!");
            }


        } else {
            MessageUtil.sendMessage(player, "<red>Could not find PvPData matching the UUID '" + target.getUniqueId() + "'");
        }

    }

}
