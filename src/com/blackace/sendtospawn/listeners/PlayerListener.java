package com.blackace.sendtospawn.listeners;

import com.blackace.sendtospawn.SendToSpawn;
import com.blackace.sendtospawn.commands.CommandSendToSpawn;
import com.earth2me.essentials.spawn.SpawnStorage;
import net.ess3.api.IEssentials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.PluginManager;


/**
 * Created by Black Ace on 11/10/2015.
 */
public class PlayerListener implements Listener {

    private transient IEssentials ess;
    private transient SpawnStorage storage;

    public PlayerListener(){
        Bukkit.getServer().getPluginManager().registerEvents(this, SendToSpawn.instance);
        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        this.ess = (IEssentials)pluginManager.getPlugin("Essentials");
        if(this.ess != null) {
            this.storage = new SpawnStorage(this.ess);
        }
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e){
        if(!CommandSendToSpawn.spawnList.contains(e.getPlayer().getName())) return;
        if(this.ess == null || this.storage == null){
            Location spawn = Bukkit.getServer().getWorlds().get(0).getSpawnLocation();
            e.getPlayer().teleport(spawn);
        } else {
            Location spawn = storage.getSpawn("default");
            e.getPlayer().teleport(spawn);
        }
        CommandSendToSpawn.spawnList.remove(e.getPlayer().getName());
    }

    /*
    @EventHandler
    public void onPlayerLoginEvent(PlayerLoginEvent e){
        if(!CommandSendToSpawn.spawnList.contains(e.getPlayer().getName())) return;
        if(this.ess == null || this.storage == null){
            Location spawn = Bukkit.getServer().getWorlds().get(0).getSpawnLocation();
            e.getPlayer().teleport(spawn);
        } else {
            Location spawn = storage.getSpawn("default");
            e.getPlayer().teleport(spawn);
        }
        CommandSendToSpawn.spawnList.remove(e.getPlayer().getName());
    }
    */
}
