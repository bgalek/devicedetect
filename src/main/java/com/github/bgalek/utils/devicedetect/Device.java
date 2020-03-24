package com.github.bgalek.utils.devicedetect;

public interface Device {
    String getValue();

    default Device getDevice() {
        return this;
    }
}
