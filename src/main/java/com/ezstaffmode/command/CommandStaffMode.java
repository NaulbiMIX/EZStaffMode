package com.ezstaffmode.command;

import java.util.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.command.*;
import com.ezstaffmode.util.*;
import com.ezstaffmode.main.*;
import org.bukkit.inventory.*;


public class CommandStaffMode implements CommandExecutor {

    private EZStaffMode main = EZStaffMode.main;
    private ItemUtil util = new ItemUtil();

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(!(s instanceof Player)) {
            s.sendMessage("§cOnly players can use this command!");
            return true;
        }

        if(!s.hasPermission("ezstaffmode.staffmode")) {
            s.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("no-permission")));
            return true;
        }

        if(args.length == 0) {
            // We want to load and save players inventory
            final Player player = (Player) s;
            if(!main.getIsInStaffMode().contains(player)) {
                main.getIsInStaffMode().add(player);
                main.getConfig().createSection("inventory." + player.getUniqueId().toString());
                for(int i = 0; i <player.getInventory().getSize(); i++) {
                    if(player.getInventory().getItem(i) != null) {
                        main.getConfig().set("inventory." + player.getUniqueId().toString() + ".items", player.getInventory().getItem(i));
                    }

                }
                // Load inventory now
                loadStaffModeInventory(player);
                s.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("enter-staff-mode")));
                return true;
            } // end !main.getInStaffMode

            if(main.getIsInStaffMode().contains(player)) {
                main.getIsInStaffMode().remove(player);
                // Give back items, actually load first then delete
                loadInventoryForPlayer(player);
                s.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("leave-staff-mode")));

                main.getConfig().set("inventory." + player.getUniqueId().toString(), null);
                main.saveConfig();
                main.reloadConfig();

            }
        }

        if(args.length != 0) {
            s.sendMessage("§c/sm || /staffmode");

        }
        return false;
    }

    private void loadStaffModeInventory(Player player) {
        // Clear their items?
        player.getInventory().clear();
        player.getInventory().setItem(0, util.createItemWithLoreAndShort(Material.COMPASS, 1, 0, "§c§lTELEPORT", null));
        player.getInventory().setItem(1, util.createItemWithLoreAndShort(Material.WOOD_AXE, 1, 0, "§c§lWORLEDIT", null));
        player.getInventory().setItem(2, util.createItemWithLoreAndShort(Material.ICE, 1, 0, "§c§lFREEZE", null));
        player.getInventory().setItem(3, util.createItemWithLoreAndShort(Material.BOOK, 1, 0, "§c§lINVENTORY VIEW", null));
        player.getInventory().setItem(8, util.createItemWithLoreAndShort(Material.INK_SACK, 1, 10, "§c§lVANISH", null));
    }

    private void loadInventoryForPlayer(Player player) {
        player.getInventory().clear();
        for(int i = 0; i < player.getInventory().getSize(); i++) {
            if(main.getConfig().getItemStack("inventory." + player.getUniqueId().toString() + ".items." + i) != null) {
                player.getInventory().addItem(main.getConfig().getItemStack("inventory." + player.getUniqueId().toString() + ".items." + i));
            }
        }
    }

}
