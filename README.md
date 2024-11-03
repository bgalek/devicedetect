# Java port of [varnish-devicedetect](https://github.com/varnishcache/varnish-devicedetect)
> Simple, fast and 0 dependencies library for device detection. 

[![Build](https://github.com/bgalek/devicedetect/actions/workflows/build.yml/badge.svg)](https://github.com/bgalek/devicedetect/actions/workflows/build.yml)
![Codecov](https://img.shields.io/codecov/c/github/bgalek/devicedetect.svg?style=flat-square)
![GitHub Release Date](https://img.shields.io/github/release-date/bgalek/devicedetect.svg?style=flat-square)
![Scrutinizer code quality](https://img.shields.io/scrutinizer/g/bgalek/devicedetect.svg?style=flat-square)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=bgalek_devicedetect&metric=alert_status)](https://sonarcloud.io/dashboard?id=bgalek_devicedetect)

## Why?
When comes to user agent parsing and normalization, sometimes few simple regex matches are enough good.
Just like in [varnish-devicedetect](https://github.com/varnishcache/varnish-devicedetect). 

This java library is an adaptation of this regular expressions list.
Test cases origin from a varnish webserver with [varnish-devicedetect](https://github.com/varnishcache/varnish-devicedetect) installed.
This library is almost 100% consistent (a little more things are considered as bot).

## Accuracy
Few hundred thousands of requests were checked against [varnish-devicedetect](https://github.com/varnishcache/varnish-devicedetect) and [yauua](https://github.com/nielsbasjes/yauaa).
There was about 0.3% of differences. 

Maybe it's enough for You too!

Feel free to explore library test data.

## Performance
Other libraries try to be as much accurate as possible. 
They extract more information from `User-Agent` header and they use clever tricks to be performant.
Most of them are decent piece of software. But sometimes, a much simpler approach will be acceptable.

## Usage
Add library dependency:
```groovy
compile "com.github.bgalek.utils:devicedetect:1.0.0"
```

Detect using default detectors:
```java
DeviceDetect.basic().detect("Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36")
```

Provide custom detections:
```java
DeviceDetect.custom().withCustomDetectors(List.of()).detect("Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36")
```

## Performance

Library is considered thread-safe.

JMH benchmark is included in project. 

That's the result:
 
| Benchmark                            | Mode  | Cnt  | Score  | Error   | Units |
| :----------------------------------- | ----- | ---- | ------ | ------- | ----- |
| DevicesDetectBenchmark.deviceDetect  | thrpt |  25  | 23.352 | ± 2.876 | ops/s |
| DevicesDetectBenchmark.yauaa         | thrpt |  25  | 1.700  | ± 0.125 | ops/s |

***REMEMBER: The numbers above are just data. To gain reusable insights, you need to follow up on 
why the numbers are the way they are. Do not assume the numbers tell you what you want them to tell.***

\* - cache disabled
