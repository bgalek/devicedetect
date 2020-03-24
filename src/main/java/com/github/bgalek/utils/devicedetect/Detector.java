package com.github.bgalek.utils.devicedetect;

import java.util.function.Predicate;

public interface Detector extends Predicate<String> {

    @Override
    boolean test(String userAgent);

    Device getDevice();
}
