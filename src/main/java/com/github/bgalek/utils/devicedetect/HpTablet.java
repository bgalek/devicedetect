package com.github.bgalek.utils.devicedetect;

import java.util.List;
import java.util.regex.Pattern;

class HpTablet implements PatternDetector {

    public static final List<Pattern> PATTERNS = List.of(Pattern.compile("hp-tablet.*TouchPad"));

    @Override
    public List<Pattern> getPatterns() {
        return PATTERNS;
    }

    @Override
    public DefaultDevices getDevice() {
        return DefaultDevices.TABLET_HP;
    }
}
