package com.bollinger.strategies.util;

import java.math.BigDecimal;

import com.bollinger.beans.BollingerState;

/**
 * A util to compare a {@link BollingerState}'s closing price with the bands of that state.
 */
public class ComparisonUtil {
    public static boolean isCloseAbove(Band band, BollingerState state) {
        return compareCloseTo(band, state) == 1;
    }

    public static boolean isCloseAboveOrEqual(Band band, BollingerState state) {
        int result = compareCloseTo(band, state);
        return result == 1 || result == 0;
    }

    public static boolean isCloseEqual(Band band, BollingerState state) {
        return compareCloseTo(band, state) == 0;
    }

    public static boolean isCloseBelowOrEqual(Band band, BollingerState state) {
        int result = compareCloseTo(band, state);
        return result == 0 || result == -1;
    }

    public static boolean isCloseBelow(Band band, BollingerState state) {
        return compareCloseTo(band, state) == -1;
    }

    private static int compareCloseTo(Band band, BollingerState state) {
        BigDecimal toCompare;
        if (band == Band.UPPER_BAND) {
            toCompare = state.getUpperBand();
        } else if (band == Band.MOVING_AVERAGE) {
            toCompare = state.getMovingAverage();
        } else if (band == Band.LOWER_BAND) {
            toCompare = state.getLowerBand();
        } else {
            throw new IllegalArgumentException("Unknown band to compare.");
        }

        return state.getClose().compareTo(toCompare);
    }

    public enum Band {
        UPPER_BAND,
        MOVING_AVERAGE,
        LOWER_BAND;
    }
}
