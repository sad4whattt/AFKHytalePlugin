package com.snipr.afkplugin.managers;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

/**
 * Manages AFK status for players.
 */
public class AFKManager {
    
    private final Set<String> afkPlayers = new HashSet<>();
    
    /**
     * Toggles AFK status for a player.
     * 
     * @param playerRef The player reference
     * @return true if player is now AFK, false if they returned
     */
    public boolean toggleAFK(PlayerRef playerRef) {
        String username = playerRef.getUsername();
        
        if (afkPlayers.contains(username)) {
            // Remove AFK
            afkPlayers.remove(username);
            return false;
        } else {
            // Set AFK
            afkPlayers.add(username);
            return true;
        }
    }
    
    /**
     * Toggles AFK status for a player by username.
     * 
     * @param username The player's username
     * @return true if player is now AFK, false if they returned
     */
    public boolean toggleAFKByUsername(String username) {
        if (afkPlayers.contains(username)) {
            // Remove AFK
            afkPlayers.remove(username);
            return false;
        } else {
            // Set AFK
            afkPlayers.add(username);
            return true;
        }
    }
    
    /**
     * Removes AFK status from a player if they are AFK.
     * 
     * @param playerRef The player reference
     * @return true if player was AFK and is now returned, false otherwise
     */
    public boolean removeAFK(PlayerRef playerRef) {
        String username = playerRef.getUsername();
        
        if (afkPlayers.contains(username)) {
            afkPlayers.remove(username);
            
            // Announce return to all players
            broadcastReturn(username);
            return true;
        }
        
        return false;
    }
    
    /**
     * Checks if a player is AFK.
     * 
     * @param playerRef The player reference
     * @return true if player is AFK
     */
    public boolean isAFK(PlayerRef playerRef) {
        return afkPlayers.contains(playerRef.getUsername());
    }
    
    /**
     * Broadcasts when a player goes AFK.
     * 
     * @param username The username of the AFK player
     */
    public void broadcastAFK(String username) {
        Message afkMessage = Message.join(
            Message.raw(username).color(Color.YELLOW),
            Message.raw(" is now afk!").color(Color.GREEN)
        );
        
        // Send to all online players
        Universe.get().getDefaultWorld().execute(() -> {
            Universe.get().getDefaultWorld().getPlayers().forEach(player -> {
                player.sendMessage(afkMessage);
            });
        });
    }
    
    /**
     * Broadcasts a player's return from AFK to all players.
     * 
     * @param username The username of the returning player
     */
    public void broadcastReturn(String username) {
        Message returnMessage = Message.join(
            Message.raw("⚡ ").color(Color.YELLOW),
            Message.raw(username).color(Color.GREEN),
            Message.raw(" is no longer AFK").color(Color.GRAY)
        );
        
        // Send to all online players
        Universe.get().getDefaultWorld().execute(() -> {
            Universe.get().getDefaultWorld().getPlayers().forEach(player -> {
                player.sendMessage(returnMessage);
            });
        });
    }
}
