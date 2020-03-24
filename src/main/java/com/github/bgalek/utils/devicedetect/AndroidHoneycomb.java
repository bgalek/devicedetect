package com.github.bgalek.utils.devicedetect;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Android 3/honeycomb was just about tablet-only,
 * and any phones will probably handle a bigger page layout.
 */
class AndroidHoneycomb implements PatternDetector {

    private static final List<Pattern> PATTERNS = List.of(Pattern.compile("(?i)android 3"));

    @Override
    public List<Pattern> getPatterns() {
        return PATTERNS;
    }

    @Override
    public DefaultDevices getDevice() {
        return DefaultDevices.TABLET_ANDROID;
    }
}
