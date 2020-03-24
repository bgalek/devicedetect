package com.github.bgalek.utils.devicedetect;

import java.util.List;
import java.util.regex.Pattern;

class MobileGeneric implements PatternDetector {

    private static final List<Pattern> PATTERNS = List.of(
            Pattern.compile("(?i)symbian"),
            Pattern.compile("(?i)^sonyericsson"),
            Pattern.compile("(?i)^nokia"),
            Pattern.compile("(?i)^samsung"),
            Pattern.compile("(?i)^lg"),
            Pattern.compile("(?i)bada"),
            Pattern.compile("(?i)blazer"),
            Pattern.compile("(?i)cellphone"),
            Pattern.compile("(?i)iemobile"),
            Pattern.compile("(?i)midp-2.0"),
            Pattern.compile("(?i)u990"),
            Pattern.compile("(?i)netfront"),
            Pattern.compile("(?i)opera mini"),
            Pattern.compile("(?i)palm"),
            Pattern.compile("(?i)nintendo wii"),
            Pattern.compile("(?i)playstation portable"),
            Pattern.compile("(?i)portalmmm"),
            Pattern.compile("(?i)proxinet"),
            Pattern.compile("(?i)windows ?ce"),
            Pattern.compile("(?i)winwap"),
            Pattern.compile("(?i)eudoraweb"),
            Pattern.compile("(?i)htc"),
            Pattern.compile("(?i)240x320"),
            Pattern.compile("(?i)avantgo")
    );

    @Override
    public List<Pattern> getPatterns() {
        return PATTERNS;
    }

    @Override
    public DefaultDevices getDevice() {
        return DefaultDevices.MOBILE_GENERIC;
    }
}
