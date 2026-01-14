package com.snipr.afkplugin.commands;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.snipr.afkplugin.managers.AFKManager;

import javax.annotation.Nonnull;
import java.awt.Color;

public class AFKCommand extends CommandBase {
    
    private final AFKManager afkManager;
    
    public AFKCommand(AFKManager afkManager) {
        super("afk", "Toggle AFK status");
        this.afkManager = afkManager;
    }
    
    @Override
    protected void executeSync(@Nonnull CommandContext context) {
        String senderName = context.sender().getDisplayName();
        boolean isNowAFK = afkManager.toggleAFKByUsername(senderName);
        
        if (isNowAFK) {
            afkManager.broadcastAFK(senderName);
        } else {
            afkManager.broadcastReturn(senderName);
        }
    }
}
