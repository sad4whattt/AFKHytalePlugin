package com.snipr.afkplugin.listeners;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent;
import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent.Formatter;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.snipr.afkplugin.AFKPlugin;
import com.snipr.afkplugin.managers.AFKManager;

import javax.annotation.Nonnull;
import java.awt.Color;

public class AFKChatListener {
    
    private static final AFKFormatter AFK_FORMATTER = new AFKFormatter();
    private static final NormalFormatter NORMAL_FORMATTER = new NormalFormatter();
    
    public static void onPlayerChat(@Nonnull PlayerChatEvent event) {
        AFKManager afkManager = AFKPlugin.getAFKManager();
        event.setFormatter(AFK_FORMATTER);
    }
    
    private static class AFKFormatter implements Formatter {
        @Override
        @Nonnull
        public Message format(@Nonnull PlayerRef playerRef, @Nonnull String message) {
            AFKManager afkManager = AFKPlugin.getAFKManager();
            
            boolean wasAFK = afkManager.isAFK(playerRef);
            
            afkManager.removeAFK(playerRef);
            
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
