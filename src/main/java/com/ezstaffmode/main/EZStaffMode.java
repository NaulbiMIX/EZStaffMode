package com.ezstaffmode.main;

import java.util.*;

import com.ezstaffmode.command.CommandFreeze;
import com.ezstaffmode.command.CommandStaffMode;
import com.ezstaffmode.command.CommandUnfreeze;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.plugin.java.*;

public class EZStaffMode extends JavaPlugin {

    private ArrayList<Player> frozenPlayer;
    private ArrayList<Player> isInStaffMode;
    public static EZStaffMode main;

    @Override
    public void onEnable() {
        if(!getDataFolder().exists()) { // saveDefaultConfig usage???
            getDataFolder().mkdirs();
            saveResource("config.yml", false);
        }
        frozenPlayer = new ArrayList<>(); // xd pendosinka eblan?
        isInStaffMode = new ArrayList<>();
        registerCommands();
        getLogger().info("=====  EZStaffMode has been enabled =====");
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        getLogger().info("=====  EZStaffMode has been disabled =====");
    }

    private void registerEvents(){

    }

    private void registerCommands(){
        getCommand("freeze").setExecutor(new CommandFreeze());
        getCommand("staffmode").setExecutor(new CommandStaffMode());
        getCommand("unfreeze").setExecutor(new CommandUnfreeze());
    }

    public ArrayList<Player> getFrozenPlayer() {
        return frozenPlayer;
    }

    public ArrayList<Player> getIsInStaffMode() {
        return isInStaffMode;
    }
}
