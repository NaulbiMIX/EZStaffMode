package com.ezstaffmode.command;

import com.ezstaffmode.main.EZStaffMode;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandUnfreeze implements CommandExecutor {

    private EZStaffMode main = EZStaffMode.main;

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (!s.hasPermission("ezstaffmode.unfreeze")) {
            s.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("no-permission")));
            return true;
        }

        if(args.length == 0) {
            s.sendMessage("§c/unfreeze <player>");
            return true;
        }

        if(args.length == 1) {
            String playerName = args[0];
            if(Bukkit.getPlayer(playerName) == null || !Bukkit.getPlayer(playerName).isOnline()) {
                s.sendMessage("§cPlayer '" + playerName + "' could not be found!");
                return true;
            }

            if(main.getFrozenPlayer().contains(Bukkit.getPlayer(playerName))) {
                main.getFrozenPlayer().remove(Bukkit.getPlayer(playerName));

                String to_unfrozen_player = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("unfreezed-player"));
                String to_sender = ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("unfreeze-send-message").replace("{player}", playerName));

                s.sendMessage(to_sender);
                Bukkit.getPlayer(playerName).sendMessage(to_unfrozen_player);
                return true;
            }else{
                s.sendMessage("§cPlayer " + playerName + " is not frozen!");
                return true;
            }
        }

        return false;
    }
}
