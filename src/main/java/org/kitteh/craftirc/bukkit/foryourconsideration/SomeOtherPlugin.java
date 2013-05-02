package org.kitteh.craftirc.bukkit.foryourconsideration;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.RegisteredServiceProvider;

public class SomeOtherPlugin implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        // I don't want anything going through. I'm a terrible plugin.
        for (final RegisteredServiceProvider<ExternalChatControl> rsp : Bukkit.getServer().getServicesManager().getRegistrations(ExternalChatControl.class)) {
            rsp.getProvider().markEventSilent(event);
        }
    }
}
