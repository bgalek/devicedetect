package com.github.bgalek.utils.devicedetect;

import java.util.List;
import java.util.regex.Pattern;

class WindowsTablet implements PatternDetector {

    public static final List<Pattern> PATTERNS = List.of(
            Pattern.compile("Touch.+Tablet PC"),
            Pattern.compile("Windows NT [0-9.]+; ARM;")
    );

    @Override
    public List<Pattern> getPatterns() {
        return PATTERNS;
    }

    @Override
    public DefaultDevices getDevice() {
        return DefaultDevices.TABLET_MICROSOFT;
    }
}
