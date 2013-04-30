package org.kitteh.craftirc.bukkit;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.kitteh.craftirc.CraftIRC;
import org.kitteh.craftirc.EndPoint;
import org.kitteh.craftirc.EndPoint.DataSingle;
import org.kitteh.craftirc.EndPoint.EndPointTypeGame;
import org.kitteh.craftirc.api.EndPointType;
import org.kitteh.craftirc.api.Plugin;

public final class BukkitPlugin extends JavaPlugin implements Plugin, Listener {
    private CraftIRC main;

    @Override
    public CraftIRC getCraftIRC() {
        return this.main;
    }

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        // Do me last!
        this.main = new CraftIRC(this);
    }

    @Override
    public void sendMessage(EndPoint<? extends EndPointType> endpoint, String... messages) {
        if (endpoint.getType() instanceof EndPointTypeGame) {
            @SuppressWarnings("unchecked")
            final EndPoint<EndPointTypeGame> gamepoint = (EndPoint<EndPointTypeGame>) endpoint;
            switch (gamepoint.getType()) {
                case GAME:
                    for (final Player player : this.getServer().getOnlinePlayers()) {
                        player.sendMessage(messages);
                    }
                    break;
                case PERMISSION:
                    for (final Player player : this.getServer().getOnlinePlayers()) {
                        if (player.hasPermission(endpoint.getData().get(DataSingle.NAME))) {
                            player.sendMessage(messages);
                        }
                    }
                    break;
                case PLAYER:
                    final Player player = this.getServer().getPlayerExact(endpoint.getData().get(DataSingle.NAME));
                    if (player != null) {
                        player.sendMessage(messages);
                    }
                    break;
                default:
                    break;
            }
        } else {
            throw new IllegalArgumentException("Expected Game EndPoint, received unknown");
        }
    }
    
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        
    }
}