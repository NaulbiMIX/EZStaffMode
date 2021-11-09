package com.ezstaffmode.command;

import com.ezstaffmode.main.EZStaffMode;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class CommandFreeze implements CommandExecutor {

    private EZStaffMode main = EZStaffMode.main;

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(!s.hasPermission("ezstaffmode.freeze")) {
            s.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("no-permission")));
    }

        if(args.length == 0) {
            s.sendMessage("§c/freeze <player>");
            return true;
        }

        if(args.length == 1) {
            // freeze <player>
            String playerName = args[0];
            if(Bukkit.getPlayer(playerName) != null && Bukkit.getPlayer(playerName).isOnline()) { // пендос шарит, если одно условие не исполняется, то второе ворк не будет, поэтому если гетПлауер нулл, то isOnline не будет чекать...
                if(main.getFrozenPlayer().contains(Bukkit.getPlayer(playerName))) {
                    s.sendMessage("§c" + playerName + " is already frozen!");
                    return true;
                }

                String to_frozen_player = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("frozen-player"));
                String to_sender = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("freeze-send-message").replace("{player}", s.getName()));

                main.getFrozenPlayer().add(Bukkit.getPlayer(playerName));
                s.sendMessage(to_sender);
                Bukkit.getPlayer(playerName).sendMessage(to_frozen_player);

                return true;
            }else{
                s.sendMessage("§cPlayer '" + playerName + "' is not online!");
                return true;
            }
        }

        return false; // ladno
    }

}
