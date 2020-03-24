package com.github.bgalek.utils.devicedetect;

import java.util.List;
import java.util.regex.Pattern;

public interface PatternDetector extends Detector {

    @Override
    default boolean test(String userAgent) {
        return getPatterns().stream().anyMatch(pattern -> pattern.matcher(userAgent).find());
    }

    List<Pattern> getPatterns();
}
