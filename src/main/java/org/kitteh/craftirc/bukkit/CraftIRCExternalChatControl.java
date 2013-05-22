package org.kitteh.craftirc.bukkit;

import java.util.HashSet;
import java.util.Map;
import java.util.WeakHashMap;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import shared.ExternalChatControl;

public class CraftIRCExternalChatControl implements ExternalChatControl, Listener {
    private final Map<AsyncPlayerChatEvent, Object> map = new WeakHashMap<AsyncPlayerChatEvent, Object>();
    private static final Object NOPE = new Object();

    @Override
    public void markEventSilent(AsyncPlayerChatEvent event) {
        this.map.put(event, CraftIRCExternalChatControl.NOPE);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onChat(AsyncPlayerChatEvent event) {
        for (final AsyncPlayerChatEvent e : new HashSet<AsyncPlayerChatEvent>(this.map.keySet())) {
            if (event == e) {
                return; // Get to tha choppa
            }
        }
        // TODO Process event
    }
}