package com.github.bgalek.utils.devicedetect;

import java.util.List;
import java.util.regex.Pattern;

class MobileSmartphone implements PatternDetector {

    private static final List<Pattern> PATTERNS = List.of(
            Pattern.compile("(?i)^HTC"),
            Pattern.compile("(?i)Fennec"),
            Pattern.compile("(?i)IEMobile"),
            Pattern.compile("(?i)BlackBerry"),
            Pattern.compile("(?i)BB10.*Mobile"),
            Pattern.compile("(?i)GT-.*Build/GINGERBREAD"),
            Pattern.compile("(?i)SymbianOS.*AppleWebKit")
    );

    @Override
    public List<Pattern> getPatterns() {
        return PATTERNS;
    }

    @Override
    public DefaultDevices getDevice() {
        return DefaultDevices.MOBILE_SMARTPHONE;
    }
}
