package com.github.bgalek.utils.devicedetect;

class RimTablet implements Detector {
    @Override
    public boolean test(String userAgent) {
        return userAgent.toLowerCase().contains("play book; u; rim tablet");
    }

    @Override
    public DefaultDevices getDevice() {
        return DefaultDevices.TABLET_RIM;
    }
}
