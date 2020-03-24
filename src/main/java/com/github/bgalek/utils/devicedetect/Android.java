package com.github.bgalek.utils.devicedetect;

import java.util.List;
import java.util.regex.Pattern;

/**
 * how do we differ between an android phone and an android tablet?
 * http://stackoverflow.com/questions/5341637/how-do-detect-android-tablets-in-general-useragent
 */
class Android implements PatternDetector {

    private static final List<Pattern> PATTERNS = List.of(Pattern.compile("(?i)android.*(mobile|mini)"));

    @Override
    public List<Pattern> getPatterns() {
        return PATTERNS;
    }

    @Override
    public DefaultDevices getDevice() {
        return DefaultDevices.MOBILE_ANDROID;
    }
}
