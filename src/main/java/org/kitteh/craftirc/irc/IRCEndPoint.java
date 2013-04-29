package org.kitteh.craftirc.irc;

import org.kitteh.craftirc.CraftIRC;
import org.kitteh.craftirc.EndPoint;
import org.kitteh.craftirc.EndPoint.DataBot;
import org.kitteh.craftirc.EndPoint.EndPointTypeIRC;
import org.kitteh.craftirc.EndPointHandler;
import org.kitteh.craftirc.api.EndPointType;

public class IRCEndPoint implements EndPointHandler {
    private final CraftIRC craftIRC;

    public IRCEndPoint(CraftIRC craftIRC) {
        this.craftIRC = craftIRC;
    }

    @Override
    public void sendMessage(EndPoint<? extends EndPointType> endpoint, String... messages) {
        if (endpoint.getType() instanceof EndPointTypeIRC) {
            final Bot bot = this.craftIRC.getBot(endpoint.getData().get(DataBot.BOT));
            final String name = endpoint.getData().get(DataBot.NAME);
            if (bot == null) {
                // TODO maybe panic
                return;
            }
            if (endpoint.getType() == EndPointTypeIRC.CHANNEL) {
                if (!name.startsWith("#")) {
                    // TODO PANIC, also make sure actually present in channel
                }
            }
            for (final String message : messages) {
                bot.sendMessage(name, message);
            }
        } else {
            throw new IllegalArgumentException("Expected IRC EndPoint, received unknown");
        }
    }
}