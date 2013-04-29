package org.kitteh.craftirc;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.Validate;
import org.kitteh.craftirc.EndPoint.EndPointTypeGame;
import org.kitteh.craftirc.EndPoint.EndPointTypeIRC;
import org.kitteh.craftirc.api.EndPointType;
import org.kitteh.craftirc.irc.IRCEndPoint;

public final class PathManager {
    private final CraftIRC craftIRC;
    private final IRCEndPoint ircEndPoint;
    private final Map<EndPoint<? extends EndPointType>, List<Path>> endPointMap = new ConcurrentHashMap<EndPoint<? extends EndPointType>, List<Path>>();
    private final Map<EndPointType, EndPointHandler> handlers = new ConcurrentHashMap<EndPointType, EndPointHandler>();

    public PathManager(CraftIRC craftIRC) {
        this.craftIRC = craftIRC;
        this.ircEndPoint = new IRCEndPoint(this.craftIRC);
        for (final EndPointTypeIRC type : EndPointTypeIRC.values()) {
            this.handlers.put(type, this.ircEndPoint);
        }
        for (final EndPointTypeGame type : EndPointTypeGame.values()) {
            this.handlers.put(type, craftIRC.getPlugin());
        }
    }

    public void registerEndPointType(EndPointType type, EndPointHandler handler) {
        Validate.notNull(type, "Endpoint type cannot be null");
        Validate.notNull(handler, "Handler cannot be null");
        this.handlers.put(type, handler);
    }

    public void registerPath(Path path) {
        Validate.notNull(path, "Path cannot be null");
        if (!this.endPointMap.containsKey(path.getSource())) {
            this.endPointMap.put(path.getSource(), new LinkedList<Path>());
        }
    }

    public void unregisterEndPointType(EndPointType type) {
        Validate.notNull(type, "Endpoint type cannot be null");
        if (type instanceof EndPointTypeIRC) {
            this.handlers.put(type, this.ircEndPoint);
        } else if (type instanceof EndPointTypeGame) {
            this.handlers.put(type, this.craftIRC.getPlugin());
        } else {
            this.handlers.remove(type);
        }
    }

    public void unregisterPath(Path path) {
        Validate.notNull(path, "Path cannot be null");
        if (this.endPointMap.containsKey(path.getSource())) {
            this.endPointMap.get(path.getSource()).remove(path);
        }
    }
}