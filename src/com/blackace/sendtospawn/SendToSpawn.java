package com.blackace.sendtospawn;

import com.blackace.sendtospawn.commands.CommandSendToSpawn;
import com.blackace.sendtospawn.listeners.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Black Ace on 11/10/2015.
 */
public class SendToSpawn extends JavaPlugin {

    public static SendToSpawn instance;
    PlayerListener playerListener;

    @Override
    public void onEnable(){
        instance = this;
        playerListener = new PlayerListener();
        this.getCommand("sts").setExecutor(new CommandSendToSpawn());

    }

    @Override
    public void onDisable(){

    }

}
