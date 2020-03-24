package com.github.bgalek.utils.devicedetect;

class KindleTablet implements Detector {
    @Override
    public boolean test(String userAgent) {
        return userAgent.toLowerCase().contains("kindle");
    }

    @Override
    public DefaultDevices getDevice() {
        return DefaultDevices.TABLET_KINDLE;
    }
}
