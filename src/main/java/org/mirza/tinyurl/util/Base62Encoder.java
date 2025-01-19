package org.mirza.tinyurl.util;

public class Base62Encoder {
    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = BASE62.length();

    public static String encode(long num) {
        StringBuilder output = new StringBuilder();
        while (num > 0) {
            output.append(BASE62.charAt((int) (num % BASE)));
            num /= BASE;
        }
        return output.reverse().toString();
    }
}
