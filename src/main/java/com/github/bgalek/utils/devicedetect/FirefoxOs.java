package com.github.bgalek.utils.devicedetect;

import java.util.List;
import java.util.regex.Pattern;

class FirefoxOs implements PatternDetector {

    public static final List<Pattern> PATTERNS = List.of(Pattern.compile("Mobile.+Firefox"));

    @Override
    public List<Pattern> getPatterns() {
        return PATTERNS;
    }

    @Override
    public DefaultDevices getDevice() {
        return DefaultDevices.MOBILE_FIREFOXOS;
    }
}
