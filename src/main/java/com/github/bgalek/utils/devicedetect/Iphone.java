package com.github.bgalek.utils.devicedetect;

import java.util.List;
import java.util.regex.Pattern;

class Iphone implements PatternDetector {

    private static final List<Pattern> PATTERNS = List.of(
            Pattern.compile("(?i)ip(hone|od)")
    );

    @Override
    public List<Pattern> getPatterns() {
        return PATTERNS;
    }

    @Override
    public DefaultDevices getDevice() {
        return DefaultDevices.MOBILE_IPHONE;
    }
}
