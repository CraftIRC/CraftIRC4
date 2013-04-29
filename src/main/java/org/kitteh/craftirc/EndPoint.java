package org.kitteh.craftirc;

import java.util.Map;

import org.apache.commons.lang.Validate;
import org.kitteh.craftirc.api.EndPointData;
import org.kitteh.craftirc.api.EndPointType;

import com.google.common.collect.ImmutableMap;

public final class EndPoint<T extends EndPointType> {
    public enum DataBot implements EndPointData {
        BOT,
        NAME;
    }

    public enum DataEmpty implements EndPointData {
    }

    public enum DataSingle implements EndPointData {
        NAME;
    }

    public enum EndPointTypeGame implements EndPointType {
        GAME(DataEmpty.class),
        PERMISSION(DataSingle.class),
        PLAYER(DataSingle.class);
        private Class<? extends EndPointData> dataClass;

        private EndPointTypeGame(Class<? extends EndPointData> dataClass) {
            this.dataClass = dataClass;
        }

        @Override
        public Class<? extends EndPointData> getDataClass() {
            return this.dataClass;
        }
    }

    public enum EndPointTypeIRC implements EndPointType {
        CHANNEL(DataBot.class),
        USER(DataBot.class);
        private Class<? extends EndPointData> dataClass;

        private EndPointTypeIRC(Class<? extends EndPointData> dataClass) {
            this.dataClass = dataClass;
        }

        @Override
        public Class<? extends EndPointData> getDataClass() {
            return this.dataClass;
        }
    }

    private T type;
    private final Map<? extends EndPointData, String> data;

    /**
     * Endpoint with no data needed other than type
     * 
     * @param type
     */
    public EndPoint(T type) {
        Validate.notNull(type, "Type cannot be null");
        final EndPointData[] values = type.getDataClass().getEnumConstants();
        Validate.isTrue((values != null) && (values.length > 0), "Type " + type + " requires data");
        this.data = ImmutableMap.of();
    }

    /**
     * Endpoint with additional data
     * 
     * Map must contain all elements of the EndPointData-implementing type.
     * The EndPointData type must be an enum.
     * 
     * @param type
     * @param data
     */
    public EndPoint(T type, Map<? extends EndPointData, String> data) {
        Validate.notNull(type, "Type cannot be null");
        Validate.notNull(data, "Data cannot be null");
        for (final EndPointData key : type.getDataClass().getEnumConstants()) {
            Validate.notNull(data.get(key), "Data does not contain " + key);
        }
        this.type = type;
        this.data = ImmutableMap.copyOf(data);
    }

    /**
     * Gets the data for this endpoint
     * 
     * @return an immutable map of data for this endpoint.
     */
    public Map<? extends EndPointData, String> getData() {
        return this.data;
    }

    public T getType() {
        return this.type;
    }
}