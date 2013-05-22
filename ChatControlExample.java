import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.RegisteredServiceProvider;

import shared.ExternalChatControl;

/**
 * This is an example of using the chat control interface to prevent
 * the consideration of chat events in plugins on a server
 * implementing the ExternalChatControl system
 */
public class ChatControlExample implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (event.getMessage().contains("butts")) { // Prevent all messages containing butts

            // Iterate the collection of all RegisteredServiceProviders for the interface
            for (final RegisteredServiceProvider<ExternalChatControl> rsp : Bukkit.getServer().getServicesManager().getRegistrations(ExternalChatControl.class)) {
                rsp.getProvider().markEventSilent(event);
            }
        }
    }
}
