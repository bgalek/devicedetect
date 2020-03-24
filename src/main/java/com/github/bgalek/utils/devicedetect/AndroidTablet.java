package com.github.bgalek.utils.devicedetect;

class AndroidTablet implements Detector {

    @Override
    public boolean test(String userAgent) {
        return userAgent.toLowerCase().contains("android");
    }

    @Override
    public DefaultDevices getDevice() {
        return DefaultDevices.TABLET_ANDROID;
    }
}
