# Snipr AFKPlugin

A simple AFK (Away From Keyboard) status plugin for Hytale servers.

## Features

- **`/afk` command** - Toggle your AFK status
- **Automatic detection** - Returns you from AFK when you send a chat message
- **[AFK] prefix** - Shows `[AFK]` before your name in chat messages
- **Broadcast announcements** - Announces to all players when you go AFK or return

## Movement Detection

**Note:** The Hytale plugin API currently doesn't expose a PlayerMoveEvent, so the plugin can only detect activity when you chat. Movement-based AFK removal is not currently possible with the available API.

## Installation

1. Place `AFKPlugin-1.0.0.jar` in your server's `mods/` folder
2. Restart your server

## Usage

Type `/afk` to mark yourself as away. Everyone will see: **"YourName is now afk!"**

Your chat messages will show the `[AFK]` prefix until you send a message, which automatically removes your AFK status and announces: **"⚡ YourName is no longer AFK"**

## Author

Created by **Snipr**
