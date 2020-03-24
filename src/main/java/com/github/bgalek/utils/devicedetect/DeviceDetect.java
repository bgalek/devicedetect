package com.github.bgalek.utils.devicedetect;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeviceDetect {

    private static final List<Detector> DEFAULT_DETECTORS = List.of(
            new MobileBot(),
            new Bot(),
            new Ipad(),
            new Iphone(),
            new Android(),
            new AndroidHoneycomb(),
            new OperaMobile(),
            new AndroidTablet(),
            new RimTablet(),
            new HpTablet(),
            new KindleTablet(),
            new WindowsTablet(),
            new FirefoxOs(),
            new MobileSmartphone(),
            new MobileGeneric()
    );

    private final List<Detector> detectors;

    private DeviceDetect() {
        this(List.of());
    }

    private DeviceDetect(List<Detector> detectors) {
        this.detectors = detectors;
    }

    public static DeviceDetect basic() {
        return new DeviceDetect(DEFAULT_DETECTORS);
    }

    public static DeviceDetectBuilder custom() {
        return new DeviceDetectBuilder();
    }

    public Device detect(String userAgent) {
        if (Objects.isNull(userAgent) || userAgent.isEmpty() || userAgent.isBlank()) return DefaultDevices.DEFAULT;
        return detectors.stream()
                .filter(detector -> detector.test(userAgent))
                .findFirst()
                .map(Detector::getDevice)
                .orElse(DefaultDevices.DEFAULT);
    }

    public static class DeviceDetectBuilder {

        private List<Detector> detectors;

        private DeviceDetectBuilder() {
        }

        public DeviceDetectBuilder withCustomDetectors(List<Detector> detectors) {
            this.detectors = detectors;
            return this;
        }

        public DeviceDetect build() {
            return new DeviceDetect(Stream.concat(detectors.stream(), DEFAULT_DETECTORS.stream()).collect(Collectors.toList()));
        }

    }
}
