package org.kitteh.craftirc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class Configuration {
    public class BotConfig {
        public class ChannelConfig {
            private final String channel;
            private final List<String> onJoin;

            private ChannelConfig(String channel, List<String> onJoin) {
                this.channel = channel;
                this.onJoin = onJoin;
            }

            public String getChannel() {
                return this.channel;
            }
        }

        private String host;
        private int port;
        private String nick;
        private String ident;
        private String name;
        private String botName;
        private List<ChannelConfig> channels;

        private BotConfig(String host, int port, String nick, String ident, String name, String botName, List<ChannelConfig> channels) {

        }

        public String getBotName() {
            return this.botName;
        }

        public List<ChannelConfig> getChannels() {
            return new ArrayList<ChannelConfig>(this.channels);
        }

        public String getHost() {
            return this.host;
        }

        public String getNick() {
            return this.nick;
        }

        public int getPort() {
            return this.port;
        }
    }

    public class PathConfig {
        private String source;
        private String destination;
    }

    private final File dataFolder;

    public Configuration(File dataFolder) {
        this.dataFolder = dataFolder;
        this.load();
    }

    public List<BotConfig> getBots() {
        return null;
    }

    void load() {
        final File configFile = new File(this.dataFolder, "config.yml");
        if (!configFile.exists()) {

        }
    }
}