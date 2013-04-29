package org.kitteh.craftirc.api;

public interface EndPointType {
    Class<? extends EndPointData> getDataClass();
}