package com.vastenly.taf.util;

import java.util.Arrays;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

import static org.apache.commons.lang3.RandomStringUtils.*;

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

    public static String getRandomStringBy(RandomSymbolType symbolsType, int length) {
        String generatedText = "";
        switch (symbolsType) {
            case ALPHA:
                generatedText = getRandomAlphabetic(length);
                break;
            case ALPHA_NUMERIC:
                generatedText = getRandomAlphanumeric(length);
                break;
            case NUMERIC:
                generatedText = getRandomNumeric(length);
                break;
            case SPECIAL:
                generatedText = getRandomSpecSymbolsString(length);
                break;
        }
        return generatedText;
    }

    /**
     * Default length of string with special symbols
     */
    private static final int DEFAULT_LENGTH = 5;

    /**
     * Special symbols intervals in ASCII codes table
     */
    private static final Vector<int[]> SPEC_SYMBOLS_INTERVALS = new Vector<int[]>();

    static {
        /** ! " # $ % & ' ( ) * + - . / */
        SPEC_SYMBOLS_INTERVALS.add(new int[]{32, 47});
        /** : ; < = > ? */
        SPEC_SYMBOLS_INTERVALS.add(new int[]{58, 64});
        /** [ \ ] ^ _ ` */
        SPEC_SYMBOLS_INTERVALS.add(new int[]{91, 96});
        /** { | } ~ */
        SPEC_SYMBOLS_INTERVALS.add(new int[]{123, 126});
    }

    private static boolean isSpecSymbol(int value) {
        for (int i = 0; i < SPEC_SYMBOLS_INTERVALS.size(); i++) {
            int[] interval = SPEC_SYMBOLS_INTERVALS.get(i);
            /** Interval must have start and end indexes */
            if (interval.length < 2) {
                continue;
            }
            if (value >= interval[0] && value <= interval[1]) {
                return true;
            }
        }
        return false;
    }

    public static char getRandomSpecSymbol() {
        int value = new Random().nextInt(127);
        return (char) value;
    }

    public static String getRandomSpecSymbolsString(int length) {
        StringBuilder builder = new StringBuilder();
        int symbolsCount = length;
        while (symbolsCount > 0) {
            char randValue = getRandomSpecSymbol();
            if (isSpecSymbol(randValue)) {
                builder.append(randValue);
                symbolsCount--;
            }
        }
        return builder.toString().trim();
    }

    public static String getRandomSpecSymbolsString(int length, char[] validSpecSymbols) {
        StringBuilder builder = new StringBuilder();
        int symbolsCount = length;
        while (symbolsCount > 0) {
            char randValue = getRandomSpecSymbol();
            for (int i = 0; i < validSpecSymbols.length; i++) {
                if (randValue == validSpecSymbols[i]) {
                    builder.append(randValue);
                    symbolsCount--;
                }
            }
        }
        return builder.toString().trim();
    }

    public static String getRandomSpecSymbolsString() {
        return getRandomSpecSymbolsString(DEFAULT_LENGTH);
    }

    public static String getRandomSpecAsciiString(int length) {
        return randomAscii(length - DEFAULT_LENGTH) + getRandomSpecSymbolsString(DEFAULT_LENGTH);
    }

    public static String getRandomAlphabetic(int length) {
        return randomAlphabetic(length);
    }

    public static String getRandomNumeric(int length) {
        return randomNumeric(length);
    }

    public static String getRandomAlphanumeric(int length) {
        return randomAlphanumeric(length);
    }

    public static String getRandomHexNumeric() {
        int number = getRandomInt(16);
        if (number == 10)
            return "A";
        if (number == 11)
            return "B";
        if (number == 12)
            return "C";
        if (number == 13)
            return "D";
        if (number == 14)
            return "E";
        if (number == 15)
            return "F";
        return String.valueOf(number);
    }

    public static String getRandomSpecAlphabetic(int length) {
        return randomAlphabetic(length - DEFAULT_LENGTH) + getRandomSpecSymbolsString(DEFAULT_LENGTH);
    }

    public static final String getRandomSpecAlphanumeric(int length) {
        return randomAlphanumeric(length - DEFAULT_LENGTH) + getRandomSpecSymbolsString(DEFAULT_LENGTH);
    }

    public static final String getRandomSpecNumeric(int length) {
        return randomNumeric(length - DEFAULT_LENGTH) + getRandomSpecSymbolsString(DEFAULT_LENGTH);
    }

    public static String getRandomSpecSymbolsString(char[] validSpecSymbols) {
        return getRandomSpecSymbolsString(DEFAULT_LENGTH, validSpecSymbols);
    }

    public static String getRandomSpecAsciiString(int length, char[] validSpecSymbols) {
        return randomAscii(length - DEFAULT_LENGTH) + getRandomSpecSymbolsString(DEFAULT_LENGTH, validSpecSymbols);
    }

    public static String getRandomSpecAlphabetic(int length, char[] validSpecSymbols) {
        return randomAlphabetic(length - DEFAULT_LENGTH) + getRandomSpecSymbolsString(DEFAULT_LENGTH, validSpecSymbols);
    }

    public static final String getRandomSpecAlphanumeric(int length, char[] validSpecSymbols) {
        return randomAlphanumeric(length - DEFAULT_LENGTH) + getRandomSpecSymbolsString(DEFAULT_LENGTH, validSpecSymbols);
    }

    public static final String getRandomSpecNumeric(int length, char[] validSpecSymbols) {
        return randomNumeric(length - DEFAULT_LENGTH) + getRandomSpecSymbolsString(DEFAULT_LENGTH, validSpecSymbols);
    }

}
