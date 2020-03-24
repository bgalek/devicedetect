package com.github.bgalek.utils.devicedetect;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("DeviceDetect specification")
class DevicesDetectTest {

    @DisplayName("should successfully detect user agent")
    @ParameterizedTest(name = "\"{1}\" should be detected as {0}")
    @CsvFileSource(resources = "/popular.csv")
    void acceptance(String device, String header) {
        //given
        DeviceDetect devicedetect = DeviceDetect.basic();

        //when
        Device detect = devicedetect.detect(header);

        //then
        assertEquals(detect.getValue(), device);
    }

    @Test
    @DisplayName("should be able to use custom detector")
    void customDetectorTest() {
        //given
        DeviceDetect deviceDetect = DeviceDetect.custom().withCustomDetectors(List.of(customDetector("app.my.company", "my-app"))).build();

        //when
        Device detect = deviceDetect.detect("app.my.company/1.33.7");

        //then
        assertEquals(detect.getValue(), "my-app");
    }

    @Test
    @DisplayName("should be able to use custom pattern detector")
    void customPatternDetectorTest() {
        //given
        DeviceDetect deviceDetect = DeviceDetect.custom().withCustomDetectors(List.of(customPatternDetector("^app", "my-app"))).build();

        //when
        Device detect = deviceDetect.detect("app.my.company/1.33.7");

        //then
        assertEquals(detect.getValue(), "my-app");
    }

    @Test
    @DisplayName("should be able to use custom device")
    void customDetectorWithCustomDeviceTest() {
        //given
        DeviceDetect deviceDetect = DeviceDetect.custom().withCustomDetectors(List.of(customDetector("app.my.company", DefaultDevices.DEFAULT.getValue()))).build();

        //when
        Device detect = deviceDetect.detect("app.my.company/1.33.7");

        //then
        assertEquals(detect.getValue(), "pc");
    }

    private Detector customDetector(String prefix, String device) {
        return new Detector() {
            @Override
            public boolean test(String userAgent) {
                return userAgent.startsWith(prefix);
            }

            @Override
            public Device getDevice() {
                return () -> device;
            }
        };
    }

    private PatternDetector customPatternDetector(String regexp, String device) {
        return new PatternDetector() {
            @Override
            public List<Pattern> getPatterns() {
                return List.of(Pattern.compile(regexp));
            }

            @Override
            public Device getDevice() {
                return () -> device;
            }
        };
    }

}