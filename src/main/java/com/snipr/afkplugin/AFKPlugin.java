package com.snipr.afkplugin;

import com.hypixel.hytale.server.core.event.events.player.PlayerChatEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.snipr.afkplugin.commands.AFKCommand;
import com.snipr.afkplugin.listeners.AFKChatListener;
import com.snipr.afkplugin.managers.AFKManager;

import javax.annotation.Nonnull;

/**
 * AFKPlugin - Allows players to mark themselves as AFK.
 * 
 * Features:
 * - /afk command to toggle AFK status
 * - Displays [AFK] prefix in chat
 * - Auto-removes AFK when chatting
 * - Announces return in chat
 * 
 * @author Snipr
 */
public class AFKPlugin extends JavaPlugin {
    
    private static AFKManager afkManager;
    
    public AFKPlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }
    
    public static AFKManager getAFKManager() {
        return afkManager;
    }
    
    @Override
    protected void setup() {
        System.out.println("[Snipr AFKPlugin] Loading...");
        
        afkManager = new AFKManager();
        
        this.getCommandRegistry().registerCommand(new AFKCommand(afkManager));
        
        this.getEventRegistry().registerGlobal(PlayerChatEvent.class, AFKChatListener::onPlayerChat);
        
        System.out.println("[Snipr AFKPlugin] Loaded successfully!");
    }
}
