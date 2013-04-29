package org.kitteh.craftirc;

import java.util.Set;

import org.apache.commons.lang.Validate;
import org.kitteh.craftirc.api.EndPointType;

public class Path {
    public enum Data {
        CHAT,
        DEATH,
        JOIN,
        KILL,
        LEAVE;
    }

    private final Set<Data> enabledData;
    private final EndPoint<? extends EndPointType> source;
    private final EndPoint<? extends EndPointType> destination;

    public Path(EndPoint<? extends EndPointType> source, EndPoint<? extends EndPointType> destination, Set<Data> enabledData) {
        Validate.notNull(source, "Source cannot be null");
        Validate.notNull(destination, "Destination cannot be null");
        Validate.notNull(enabledData, "Data cannot be null");
        this.source = source;
        this.destination = destination;
        this.enabledData = enabledData;
    }

    public EndPoint<? extends EndPointType> getDestination() {
        return this.destination;
    }

    public EndPoint<? extends EndPointType> getSource() {
        return this.source;
    }

    public boolean isEnabled(Data data) {
        Validate.notNull(data, "Data cannot be null");
        return this.enabledData.contains(data);
    }
}