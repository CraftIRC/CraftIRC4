package org.kitteh.craftirc;

import org.kitteh.craftirc.api.EndPointType;

public interface EndPointHandler {
    public void sendMessage(EndPoint<? extends EndPointType> endpoint, String... messages);
}