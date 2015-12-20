package com.blackace.sendtospawn.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Black Ace on 11/10/2015.
 */
public class CommandSendToSpawn implements CommandExecutor {

    public static Set<String> spawnList = new HashSet<String>();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String cmdLabel, String[] args) {

        if(args.length == 0){
            sender.sendMessage(ChatColor.RED + "Error! This command requires arguements, please use /sts help for more information");
            return true;
        }

        if(args.length > 0 && args[0].equalsIgnoreCase("help")){
            handleHelp(sender);
            return true;
        }

        if(args.length > 0 && args[0].equalsIgnoreCase("remove")){
            handleRemove(sender, args);
            return true;
        }

        if(args.length > 0 && args[0].equalsIgnoreCase("list")){
            handleList(sender);
            return true;
        }

        if(args.length > 0) {
            handleAdd(sender, args);
            return true;
        }
        return false;
    }

    private void handleList(CommandSender sender) {
        sender.sendMessage(ChatColor.GRAY + "=--------------------=");
        if(this.spawnList.size() == 0){
            sender.sendMessage(ChatColor.RED + "List contains no entries");
        } else {
            int i = 1;
            for(String s : this.spawnList){
                sender.sendMessage(ChatColor.GOLD + "" + i + ") " + ChatColor.AQUA + s);
            }
        }
        sender.sendMessage(ChatColor.GRAY + "=--------------------=");
    }

    private void handleHelp(CommandSender sender){
        sender.sendMessage(ChatColor.GOLD + "Use /sendtospawn add <name> to add a player to the list");
        sender.sendMessage(ChatColor.GOLD + "Use /sendtospawn list to show a list of players to be sent to spawn");
        sender.sendMessage(ChatColor.GOLD + "Use /sendtospawn remove <name> to remove a player from the list");
    }

    private void handleAdd(CommandSender sender, String[] args){
        if(spawnList.contains(args[0])){
            sender.sendMessage(ChatColor.RED + "That player is already marked for spawn");
        } else {
            spawnList.add(args[0]);
            sender.sendMessage(ChatColor.GREEN + "Player " + args[1] + " added to send to spawn list");
        }

    }

    private void handleRemove(CommandSender sender, String[] args){
        if(spawnList.contains(args[1])){
            spawnList.remove(args[1]);
            sender.sendMessage(ChatColor.GREEN + "Player " + args[1] + " removed from send to spawn list");
        } else {
            sender.sendMessage(ChatColor.RED + "Player is not marked for spawn");
        }
    }

}
