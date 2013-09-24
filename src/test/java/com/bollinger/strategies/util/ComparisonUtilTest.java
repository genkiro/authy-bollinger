package com.bollinger.strategies.util;

import java.math.BigDecimal;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bollinger.beans.BollingerState;

import static com.bollinger.strategies.util.ComparisonUtil.*;
import static com.bollinger.strategies.util.ComparisonUtil.Band.*;

public class ComparisonUtilTest {

    @Test
    public void test1() {
        BollingerState state = new BollingerState();
        state.setClose(new BigDecimal("7"));
        state.setUpperBand(new BigDecimal("5"));
        state.setMovingAverage(new BigDecimal("3"));
        state.setLowerBand(new BigDecimal("1"));

        Assert.assertTrue(isCloseAbove(UPPER_BAND, state));
        Assert.assertTrue(isCloseAboveOrEqual(UPPER_BAND, state));
        Assert.assertFalse(isCloseEqual(UPPER_BAND, state));
        Assert.assertFalse(isCloseBelowOrEqual(UPPER_BAND, state));
        Assert.assertFalse(isCloseBelow(UPPER_BAND, state));

        Assert.assertTrue(isCloseAbove(MOVING_AVERAGE, state));
        Assert.assertTrue(isCloseAboveOrEqual(MOVING_AVERAGE, state));
        Assert.assertFalse(isCloseEqual(MOVING_AVERAGE, state));
        Assert.assertFalse(isCloseBelowOrEqual(MOVING_AVERAGE, state));
        Assert.assertFalse(isCloseBelow(MOVING_AVERAGE, state));

        Assert.assertTrue(isCloseAbove(LOWER_BAND, state));
        Assert.assertTrue(isCloseAboveOrEqual(LOWER_BAND, state));
        Assert.assertFalse(isCloseEqual(LOWER_BAND, state));
        Assert.assertFalse(isCloseBelowOrEqual(LOWER_BAND, state));
        Assert.assertFalse(isCloseBelow(LOWER_BAND, state));
    }

    @Test
    public void test2() {
        BollingerState state = new BollingerState();
        state.setClose(new BigDecimal("5"));
        state.setUpperBand(new BigDecimal("5"));
        state.setMovingAverage(new BigDecimal("3"));
        state.setLowerBand(new BigDecimal("1"));

        Assert.assertFalse(isCloseAbove(UPPER_BAND, state));
        Assert.assertTrue(isCloseAboveOrEqual(UPPER_BAND, state));
        Assert.assertTrue(isCloseEqual(UPPER_BAND, state));
        Assert.assertTrue(isCloseBelowOrEqual(UPPER_BAND, state));
        Assert.assertFalse(isCloseBelow(UPPER_BAND, state));

        Assert.assertTrue(isCloseAbove(MOVING_AVERAGE, state));
        Assert.assertTrue(isCloseAboveOrEqual(MOVING_AVERAGE, state));
        Assert.assertFalse(isCloseEqual(MOVING_AVERAGE, state));
        Assert.assertFalse(isCloseBelowOrEqual(MOVING_AVERAGE, state));
        Assert.assertFalse(isCloseBelow(MOVING_AVERAGE, state));

        Assert.assertTrue(isCloseAbove(LOWER_BAND, state));
        Assert.assertTrue(isCloseAboveOrEqual(LOWER_BAND, state));
        Assert.assertFalse(isCloseEqual(LOWER_BAND, state));
        Assert.assertFalse(isCloseBelowOrEqual(LOWER_BAND, state));
        Assert.assertFalse(isCloseBelow(LOWER_BAND, state));
    }

    @Test
    public void test3() {
        BollingerState state = new BollingerState();
        state.setClose(new BigDecimal("4"));
        state.setUpperBand(new BigDecimal("5"));
        state.setMovingAverage(new BigDecimal("3"));
        state.setLowerBand(new BigDecimal("1"));

        Assert.assertFalse(isCloseAbove(UPPER_BAND, state));
        Assert.assertFalse(isCloseAboveOrEqual(UPPER_BAND, state));
        Assert.assertFalse(isCloseEqual(UPPER_BAND, state));
        Assert.assertTrue(isCloseBelowOrEqual(UPPER_BAND, state));
        Assert.assertTrue(isCloseBelow(UPPER_BAND, state));

        Assert.assertTrue(isCloseAbove(MOVING_AVERAGE, state));
        Assert.assertTrue(isCloseAboveOrEqual(MOVING_AVERAGE, state));
        Assert.assertFalse(isCloseEqual(MOVING_AVERAGE, state));
        Assert.assertFalse(isCloseBelowOrEqual(MOVING_AVERAGE, state));
        Assert.assertFalse(isCloseBelow(MOVING_AVERAGE, state));

        Assert.assertTrue(isCloseAbove(LOWER_BAND, state));
        Assert.assertTrue(isCloseAboveOrEqual(LOWER_BAND, state));
        Assert.assertFalse(isCloseEqual(LOWER_BAND, state));
        Assert.assertFalse(isCloseBelowOrEqual(LOWER_BAND, state));
        Assert.assertFalse(isCloseBelow(LOWER_BAND, state));
    }

    @Test
    public void test4() {
        BollingerState state = new BollingerState();
        state.setClose(new BigDecimal("3"));
        state.setUpperBand(new BigDecimal("5"));
        state.setMovingAverage(new BigDecimal("3"));
        state.setLowerBand(new BigDecimal("1"));

        Assert.assertFalse(isCloseAbove(UPPER_BAND, state));
        Assert.assertFalse(isCloseAboveOrEqual(UPPER_BAND, state));
        Assert.assertFalse(isCloseEqual(UPPER_BAND, state));
        Assert.assertTrue(isCloseBelowOrEqual(UPPER_BAND, state));
        Assert.assertTrue(isCloseBelow(UPPER_BAND, state));

        Assert.assertFalse(isCloseAbove(MOVING_AVERAGE, state));
        Assert.assertTrue(isCloseAboveOrEqual(MOVING_AVERAGE, state));
        Assert.assertTrue(isCloseEqual(MOVING_AVERAGE, state));
        Assert.assertTrue(isCloseBelowOrEqual(MOVING_AVERAGE, state));
        Assert.assertFalse(isCloseBelow(MOVING_AVERAGE, state));

        Assert.assertTrue(isCloseAbove(LOWER_BAND, state));
        Assert.assertTrue(isCloseAboveOrEqual(LOWER_BAND, state));
        Assert.assertFalse(isCloseEqual(LOWER_BAND, state));
        Assert.assertFalse(isCloseBelowOrEqual(LOWER_BAND, state));
        Assert.assertFalse(isCloseBelow(LOWER_BAND, state));
    }

    @Test
    public void test5() {
        BollingerState state = new BollingerState();
        state.setClose(new BigDecimal("2"));
        state.setUpperBand(new BigDecimal("5"));
        state.setMovingAverage(new BigDecimal("3"));
        state.setLowerBand(new BigDecimal("1"));

        Assert.assertFalse(isCloseAbove(UPPER_BAND, state));
        Assert.assertFalse(isCloseAboveOrEqual(UPPER_BAND, state));
        Assert.assertFalse(isCloseEqual(UPPER_BAND, state));
        Assert.assertTrue(isCloseBelowOrEqual(UPPER_BAND, state));
        Assert.assertTrue(isCloseBelow(UPPER_BAND, state));

        Assert.assertFalse(isCloseAbove(MOVING_AVERAGE, state));
        Assert.assertFalse(isCloseAboveOrEqual(MOVING_AVERAGE, state));
        Assert.assertFalse(isCloseEqual(MOVING_AVERAGE, state));
        Assert.assertTrue(isCloseBelowOrEqual(MOVING_AVERAGE, state));
        Assert.assertTrue(isCloseBelow(MOVING_AVERAGE, state));

        Assert.assertTrue(isCloseAbove(LOWER_BAND, state));
        Assert.assertTrue(isCloseAboveOrEqual(LOWER_BAND, state));
        Assert.assertFalse(isCloseEqual(LOWER_BAND, state));
        Assert.assertFalse(isCloseBelowOrEqual(LOWER_BAND, state));
        Assert.assertFalse(isCloseBelow(LOWER_BAND, state));
    }

    @Test
    public void test6() {
        BollingerState state = new BollingerState();
        state.setClose(new BigDecimal("1"));
        state.setUpperBand(new BigDecimal("5"));
        state.setMovingAverage(new BigDecimal("3"));
        state.setLowerBand(new BigDecimal("1"));

        Assert.assertFalse(isCloseAbove(UPPER_BAND, state));
        Assert.assertFalse(isCloseAboveOrEqual(UPPER_BAND, state));
        Assert.assertFalse(isCloseEqual(UPPER_BAND, state));
        Assert.assertTrue(isCloseBelowOrEqual(UPPER_BAND, state));
        Assert.assertTrue(isCloseBelow(UPPER_BAND, state));

        Assert.assertFalse(isCloseAbove(MOVING_AVERAGE, state));
        Assert.assertFalse(isCloseAboveOrEqual(MOVING_AVERAGE, state));
        Assert.assertFalse(isCloseEqual(MOVING_AVERAGE, state));
        Assert.assertTrue(isCloseBelowOrEqual(MOVING_AVERAGE, state));
        Assert.assertTrue(isCloseBelow(MOVING_AVERAGE, state));

        Assert.assertFalse(isCloseAbove(LOWER_BAND, state));
        Assert.assertTrue(isCloseAboveOrEqual(LOWER_BAND, state));
        Assert.assertTrue(isCloseEqual(LOWER_BAND, state));
        Assert.assertTrue(isCloseBelowOrEqual(LOWER_BAND, state));
        Assert.assertFalse(isCloseBelow(LOWER_BAND, state));
    }

    @Test
    public void test7() {
        BollingerState state = new BollingerState();
        state.setClose(new BigDecimal("0"));
        state.setUpperBand(new BigDecimal("5"));
        state.setMovingAverage(new BigDecimal("3"));
        state.setLowerBand(new BigDecimal("1"));

        Assert.assertFalse(isCloseAbove(UPPER_BAND, state));
        Assert.assertFalse(isCloseAboveOrEqual(UPPER_BAND, state));
        Assert.assertFalse(isCloseEqual(UPPER_BAND, state));
        Assert.assertTrue(isCloseBelowOrEqual(UPPER_BAND, state));
        Assert.assertTrue(isCloseBelow(UPPER_BAND, state));

        Assert.assertFalse(isCloseAbove(MOVING_AVERAGE, state));
        Assert.assertFalse(isCloseAboveOrEqual(MOVING_AVERAGE, state));
        Assert.assertFalse(isCloseEqual(MOVING_AVERAGE, state));
        Assert.assertTrue(isCloseBelowOrEqual(MOVING_AVERAGE, state));
        Assert.assertTrue(isCloseBelow(MOVING_AVERAGE, state));

        Assert.assertFalse(isCloseAbove(LOWER_BAND, state));
        Assert.assertFalse(isCloseAboveOrEqual(LOWER_BAND, state));
        Assert.assertFalse(isCloseEqual(LOWER_BAND, state));
        Assert.assertTrue(isCloseBelowOrEqual(LOWER_BAND, state));
        Assert.assertTrue(isCloseBelow(LOWER_BAND, state));
    }

}
