package com.github.bgalek.utils.devicedetect;

import java.util.List;
import java.util.regex.Pattern;

class Bot implements PatternDetector {

    static final List<Pattern> PATTERNS = List.of(
            Pattern.compile("(?i)(ads|google|bing|msn|yandex|baidu|ro|career|seznam|aspiegel|)bot"),
            Pattern.compile("(?i)(baidu|jike|symantec|sogou web |)spider"),
            Pattern.compile("(?i)pingdom"),
            Pattern.compile("(?i)facebookexternalhit"),
            Pattern.compile("(?i)scanner"),
            Pattern.compile("(?i)slurp"),
            Pattern.compile("(?i)(web)crawler"),
            Pattern.compile("(?i)rss/?"),
            Pattern.compile("(?i)webfeedparser"),
            Pattern.compile("^(?i)python-"),
            Pattern.compile("^(?i)apache"),
            Pattern.compile("^(?i)okhttp"),
            Pattern.compile("^(?i)curl"),
            Pattern.compile("^(?i)guzzle"),
            Pattern.compile("^(?i)php/"),
            Pattern.compile("^(?i)reactornetty"),
            Pattern.compile("^(?i)scrapy"),
            Pattern.compile("^(?i)simplepie"),
            Pattern.compile("^(?i)spark/"),
            Pattern.compile("^(?i)wget"),
            Pattern.compile("^(?i)whatsapp"),
            Pattern.compile("^(?i)axios"),
            Pattern.compile("^(?i)grub-"),
            Pattern.compile("^(?i)iTunes/"),
            Pattern.compile("^(?i)libwww-"),
            Pattern.compile("^(?i)rest-"),
            Pattern.compile("^(?i)raintank collector"),
            Pattern.compile("^(?i)appengine"),
            Pattern.compile("^(?i)baseLinker/"),
            Pattern.compile("^(?i)disqus/"),
            Pattern.compile("^(?i)go-"),
            Pattern.compile("^(?i)java/"),
            Pattern.compile("^(?i)jigsaw/"),
            Pattern.compile("^(?i)lynx/"),
            Pattern.compile("^(?i)microsoft office"),
            Pattern.compile("^(?i)symfony browserkit"),
            Pattern.compile("^(?i)webcopier"),
            Pattern.compile("^(?i)wordpress"),
            Pattern.compile("^(?i)microsoft windows network diagnostics"),
            Pattern.compile("^(?i)user-agent:"),
            Pattern.compile("^(?i)webfeedparser"),
            Pattern.compile("^(?i)httpclient-?/")
    );

    @Override
    public List<Pattern> getPatterns() {
        return PATTERNS;
    }

    @Override
    public DefaultDevices getDevice() {
        return DefaultDevices.BOT;
    }
}
