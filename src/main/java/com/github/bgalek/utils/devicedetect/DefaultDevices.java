package com.github.bgalek.utils.devicedetect;

public enum DefaultDevices implements Device {

    BOT("bot"),
    MOBILE_BOT("mobile-bot"),
    MOBILE_IPHONE("mobile-iphone"),
    TABLET_IPAD("tablet-ipad"),
    MOBILE_ANDROID("mobile-android"),
    MOBILE_FIREFOXOS("mobile-firefoxos"),
    MOBILE_SMARTPHONE("mobile-smartphone"),
    MOBILE_GENERIC("mobile-generic"),
    TABLET_MICROSOFT("tablet-microsoft"),
    TABLET_ANDROID("tablet-android"),
    TABLET_HP("tablet-hp"),
    TABLET_KINDLE("tablet-kindle"),
    TABLET_RIM("tablet-rim"),
    PC("pc");

    static final DefaultDevices DEFAULT = PC;

    private final String value;

    DefaultDevices(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
