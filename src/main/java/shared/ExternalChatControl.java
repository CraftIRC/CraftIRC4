package shared;

import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * THIS CLASS IS NOT READY FOR PUBLIC CONSUMPTION
 * Below text will apply in the future
 *
 * This class should not be modified in any way.
 * Should not be repackaged, abused, misused.
 * Do not rename anything.
 *
 * Instead, implement this interface in your plugin and register it with the
 * Bukkit services API. Then, plugins can interact with the services API to
 * inform all plugins on the server implementing this interface to not
 * consider a particular chat event.
 */
public interface ExternalChatControl {
    public void markEventSilent(AsyncPlayerChatEvent event);
}