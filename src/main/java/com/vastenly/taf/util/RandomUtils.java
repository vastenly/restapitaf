package com.vastenly.taf.util;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    private final static int ONE = 1;
    private final static int TEN = 10;
    private final static Random random = new Random();

    public static int getRandomInt() {
        return random.nextInt();
    }

    public static int getRandomInt(int limit) {
        return random.nextInt(limit);
    }

    public static int getRandomInt(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    public static int getRandomInt(int min, int max, int[] exclude) {
        Arrays.sort(exclude);
        int rand = 0;
        do {
            rand = getRandomInt(min, max);
        } while (Arrays.binarySearch(exclude, rand) >= 0);
        return rand;
    }

    public static long getRandomLong() {
        return random.nextLong();
    }

    public static long getRandomLong(long limit) {
        long value = 0;
        while (true) {
            value = random.nextLong();
            if (value <= limit) {
                return value;
            }
        }
    }

    public static long getRandomLong(Long min, Long max) {
        return ThreadLocalRandom.current().nextLong(min, max);
    }


    public enum RandomSymbolType {

        ALPHA("ALPHA"),
        ALPHA_NUMERIC("ALPHA_NUMERIC"),
        NUMERIC("NUMERIC"),
        SPECIAL("SPECIAL");

        private String value;

        private RandomSymbolType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static RandomSymbolType getByString(String value) {
            return valueOf(value.toUpperCase());
        }
    }


}
