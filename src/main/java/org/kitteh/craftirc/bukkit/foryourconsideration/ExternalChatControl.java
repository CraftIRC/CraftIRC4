package org.kitteh.craftirc.bukkit.foryourconsideration;

import org.bukkit.event.player.AsyncPlayerChatEvent;

public interface ExternalChatControl {
    public void markEventSilent(AsyncPlayerChatEvent event);
}
