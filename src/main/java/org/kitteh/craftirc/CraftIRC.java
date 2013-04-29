package org.kitteh.craftirc;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.Validate;
import org.kitteh.craftirc.Configuration.BotConfig;
import org.kitteh.craftirc.api.Plugin;
import org.kitteh.craftirc.irc.Bot;

public final class CraftIRC {
    private final Map<String, Bot> bots = new ConcurrentHashMap<String, Bot>();
    private final Plugin plugin;
    private final Configuration config;
    private final PathManager pathManager;

    public CraftIRC(Plugin plugin) {
        Validate.notNull(plugin, "Plugin cannot be null");
        this.plugin = plugin;
        this.config = new Configuration(this.plugin.getDataFolder());
        this.pathManager = new PathManager(this);
        final List<BotConfig> bots = this.config.getBots();
        for (final BotConfig bot : bots) {
            this.bots.put(bot.getBotName(), new Bot(bot));
        }
    }

    public Bot getBot(String name) {
        Validate.notNull(name, "Bot name cannot be null");
        return this.bots.get(name);
    }

    public PathManager getPathManager() {
        return this.pathManager;
    }

    public EndPointHandler getPlugin() {
        return this.plugin;
    }
}