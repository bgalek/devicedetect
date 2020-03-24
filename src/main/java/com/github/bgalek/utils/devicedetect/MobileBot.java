package com.github.bgalek.utils.devicedetect;

import java.util.regex.Pattern;

class MobileBot implements Detector {

    private static final Pattern IPHONE_OR_WINDOWS_PHONE = Pattern.compile("(iPhone|Windows Phone)");
    private static final Pattern ANDROID_OR_IPHONE = Pattern.compile("(Android|iPhone)");
    private static final Pattern GOOGLEBOT = Pattern.compile("\\(compatible.?; Googlebot/\\d.\\d.?; \\+http://www.google.com/bot.html");
    private static final Pattern GOOGLEBOT_MOBILE = Pattern.compile("\\(compatible.?; Googlebot-Mobile/\\d.\\d.?; \\+http://www.google.com/bot.html");
    private static final Pattern GOOGLEADSBOT_MOBILE = Pattern.compile("\\(compatible.?; AdsBot-Google-Mobile; \\+http://www.google.com/mobile/adsbot.html");
    private static final Pattern BINGBOT = Pattern.compile("\\(compatible; bingbot/\\d.\\d; \\+http://www.bing.com/bingbot.htm");

    @Override
    public boolean test(String userAgent) {
        return containsGoogleBotMobile(userAgent) || (isGoogleBotMobileForAndroidOrIphone(userAgent) || isBingBotMobileForIphoneOrWindowsPhone(userAgent));
    }

    @Override
    public DefaultDevices getDevice() {
        return DefaultDevices.MOBILE_BOT;
    }

    private boolean isBingBotMobileForIphoneOrWindowsPhone(String userAgentHeaderValue) {
        return IPHONE_OR_WINDOWS_PHONE.matcher(userAgentHeaderValue).find() && containsBingBot(userAgentHeaderValue);
    }

    private boolean isGoogleBotMobileForAndroidOrIphone(String userAgentHeaderValue) {
        return ANDROID_OR_IPHONE.matcher(userAgentHeaderValue).find() && GOOGLEBOT.matcher(userAgentHeaderValue).find();
    }

    private boolean containsBingBot(String userAgentHeaderValue) {
        return BINGBOT.matcher(userAgentHeaderValue).find();
    }

    private boolean containsGoogleBotMobile(String userAgentHeaderValue) {
        return GOOGLEBOT_MOBILE.matcher(userAgentHeaderValue).find() || GOOGLEADSBOT_MOBILE.matcher(userAgentHeaderValue).find();
    }
}
