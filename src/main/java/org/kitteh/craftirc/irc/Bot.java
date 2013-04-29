package org.kitteh.craftirc.irc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.kitteh.craftirc.Configuration.BotConfig;
import org.kitteh.craftirc.Configuration.BotConfig.ChannelConfig;
import org.kitteh.irc.IRCBot;

public class Bot {
    private final IRCBot bot;

    public Bot(BotConfig config) {
        this.bot = new IRCBot(config.getBotName(), config.getHost(), config.getPort(), config.getNick());
        final List<String> channels = new ArrayList<String>(config.getChannels().size());
        for (final ChannelConfig channel : config.getChannels()) {
            channels.add(channel.getChannel());
        }
        this.bot.addChannel(channels.toArray(new String[0]));
        this.bot.start();
    }

    public void sendMessage(String channel, String message) {
        Validate.isTrue(channel.indexOf(" ") == -1, "Channel name cannot have spaces");
        this.bot.sendRawLine("PRIVMSG " + channel + " :" + message, false);
    }
}