package com.github.bgalek.utils.devicedetect;

import nl.basjes.parse.useragent.UserAgentAnalyzer;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;

public class DevicesDetectBenchmark {

    @Test
    public void benchmark() throws Exception {
        Options opt = new OptionsBuilder()
                .include(this.getClass().getName() + ".*")
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.MICROSECONDS)
                .warmupTime(TimeValue.seconds(1))
                .warmupIterations(2)
                .measurementTime(TimeValue.seconds(1))
                .measurementIterations(2)
                .threads(2)
                .forks(1)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                .build();
        new Runner(opt).run();
    }

    @Benchmark
    public void deviceDetect() {
        DeviceDetect deviceDetect = DeviceDetect.basic();
        for (int i = 0; i < 1000; i++) {
            deviceDetect.detect("Mozilla/5.0 (Macintosh; Intel Mac OS X x.y; rv:42.0) Gecko/20100101 Firefox/42.0");
        }
    }

    @Benchmark
    public void yauaa() {
        UserAgentAnalyzer uaa = UserAgentAnalyzer.newBuilder().withoutCache().withField("DeviceClass").build();
        for (int i = 0; i < 1000; i++) {
            uaa.parse("Mozilla/5.0 (Macintosh; Intel Mac OS X x.y; rv:42.0) Gecko/20100101 Firefox/42.0").get("DeviceClass");
        }
    }
}
