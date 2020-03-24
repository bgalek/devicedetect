package com.github.bgalek.utils.devicedetect;

/**
 * Opera Mobile
 */
class OperaMobile implements Detector {
    @Override
    public boolean test(String userAgent) {
        return userAgent.toLowerCase().contains("opera mobi");
    }

    @Override
    public DefaultDevices getDevice() {
        return DefaultDevices.MOBILE_SMARTPHONE;
    }
}
