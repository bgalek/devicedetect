package com.github.bgalek.utils.devicedetect;

import java.util.List;
import java.util.regex.Pattern;

class Ipad implements PatternDetector {

    private static final List<Pattern> PATTERNS = List.of(
            Pattern.compile("(?i)ipad")
    );

    @Override
    public List<Pattern> getPatterns() {
        return PATTERNS;
    }

    @Override
    public DefaultDevices getDevice() {
        return DefaultDevices.TABLET_IPAD;
    }
}
