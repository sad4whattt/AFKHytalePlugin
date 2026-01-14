package com.snipr.afkplugin.listeners;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent;
import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent.Formatter;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.snipr.afkplugin.AFKPlugin;
import com.snipr.afkplugin.managers.AFKManager;

import javax.annotation.Nonnull;
import java.awt.Color;

/**
 * Listens for chat events to display [AFK] prefix and remove AFK status.
 */
public class AFKChatListener {
    
    private static final AFKFormatter AFK_FORMATTER = new AFKFormatter();
    private static final NormalFormatter NORMAL_FORMATTER = new NormalFormatter();
    
    /**
     * Called when a player sends a chat message.
     * Adds [AFK] prefix if player is AFK, and removes AFK status.
     */
    public static void onPlayerChat(@Nonnull PlayerChatEvent event) {
        AFKManager afkManager = AFKPlugin.getAFKManager();
        
        // The formatter will receive the PlayerRef, so we set it based on current status
        // but first we need to track which player is sending this event
        // We'll use a ThreadLocal to pass the username to the formatter
        event.setFormatter(AFK_FORMATTER);
    }
    
    /**
     * Formatter for players - shows [AFK] prefix if they're AFK.
     */
    private static class AFKFormatter implements Formatter {
        @Override
        @Nonnull
        public Message format(@Nonnull PlayerRef playerRef, @Nonnull String message) {
            AFKManager afkManager = AFKPlugin.getAFKManager();
            
            // Check if this player is AFK
            boolean wasAFK = afkManager.isAFK(playerRef);
            
            // Remove them from AFK when they chat
            afkManager.removeAFK(playerRef);
            
            // Format message with [AFK] if they were AFK
            if (wasAFK) {
                return Message.join(
                    Message.raw("[AFK] ").color(Color.YELLOW),
                    Message.raw("<").color(Color.GRAY),
                    Message.raw(playerRef.getUsername()).color(Color.WHITE),
                    Message.raw("> ").color(Color.GRAY),
                    Message.raw(message).color(Color.WHITE)
                );
            } else {
                return Message.join(
                    Message.raw("<").color(Color.GRAY),
                    Message.raw(playerRef.getUsername()).color(Color.WHITE),
                    Message.raw("> ").color(Color.GRAY),
                    Message.raw(message).color(Color.WHITE)
                );
            }
        }
    }
    
    /**
     * Normal formatter - no prefix.
     */
    private static class NormalFormatter implements Formatter {
        @Override
        @Nonnull
        public Message format(@Nonnull PlayerRef playerRef, @Nonnull String message) {
            return Message.join(
                Message.raw("<").color(Color.GRAY),
                Message.raw(playerRef.getUsername()).color(Color.WHITE),
                Message.raw("> ").color(Color.GRAY),
                Message.raw(message).color(Color.WHITE)
            );
        }
    }
}
