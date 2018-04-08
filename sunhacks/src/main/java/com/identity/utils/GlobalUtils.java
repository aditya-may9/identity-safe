package com.identity.utils;

import java.util.UUID;

public class GlobalUtils {

    public static Long getCurrentTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    public static Long convertMinutesToSeconds(Long minutes) {
        return minutes * 60;
    }

    public static String maskSecret(String originalSecret) {
        // Logic can be changed
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
