package com.bollinger.strategies;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import com.google.common.collect.Lists;

public class WalkUpBandStrategyTest extends StrategyTest {

    @Override
    protected Strategy createStrategy() {
        return new WalkUpBandStrategy();
    }

    @Test
    public void test1() throws IOException {
        // Note the chronological ascending order
        List<String> closingPrices = Lists.newArrayList(
                "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20"
        );
        List<String> upperBand = Lists.newArrayList(
                "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50"
        );
        List<String> movingAverage = Lists.newArrayList(
                "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30"
        );
        List<String> lowerBand = Lists.newArrayList(
                "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10"
        );
        testStrategy(closingPrices, upperBand, movingAverage, lowerBand, 0);
    }

    @Test
    public void test2() throws IOException {
        List<String> closingPrices = Lists.newArrayList(
                "20", "50", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20"
        );
        List<String> upperBand = Lists.newArrayList(
                "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50"
        );
        List<String> movingAverage = Lists.newArrayList(
                "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30"
        );
        List<String> lowerBand = Lists.newArrayList(
                "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10"
        );
        testStrategy(closingPrices, upperBand, movingAverage, lowerBand, 35);
    }

    @Test
    public void test3() throws IOException {
        List<String> closingPrices = Lists.newArrayList(
                "20", "50", "20", "50", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20"
        );
        List<String> upperBand = Lists.newArrayList(
                "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50"
        );
        List<String> movingAverage = Lists.newArrayList(
                "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30"
        );
        List<String> lowerBand = Lists.newArrayList(
                "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10"
        );
        testStrategy(closingPrices, upperBand, movingAverage, lowerBand, 70);
    }

    @Test
    public void test4() throws IOException {
        List<String> closingPrices = Lists.newArrayList(
                "20", "50", "20", "50", "55", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20"
        );
        List<String> upperBand = Lists.newArrayList(
                "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50"
        );
        List<String> movingAverage = Lists.newArrayList(
                "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30"
        );
        List<String> lowerBand = Lists.newArrayList(
                "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10"
        );
        testStrategy(closingPrices, upperBand, movingAverage, lowerBand, 80);
    }

    @Test
    public void test5() throws IOException {
        List<String> closingPrices = Lists.newArrayList(
                "20", "50", "20", "50", "55", "50", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20"
        );
        List<String> upperBand = Lists.newArrayList(
                "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50"
        );
        List<String> movingAverage = Lists.newArrayList(
                "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30"
        );
        List<String> lowerBand = Lists.newArrayList(
                "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10"
        );
        testStrategy(closingPrices, upperBand, movingAverage, lowerBand, 90);
    }

    @Test
    public void test6() throws IOException {
        List<String> closingPrices = Lists.newArrayList(
                "20", "50", "20", "50", "55", "50", "10", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20"
        );
        List<String> upperBand = Lists.newArrayList(
                "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50"
        );
        List<String> movingAverage = Lists.newArrayList(
                "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30"
        );
        List<String> lowerBand = Lists.newArrayList(
                "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10"
        );
        testStrategy(closingPrices, upperBand, movingAverage, lowerBand, 0);
    }

    @Test
    public void test7() throws IOException {
        List<String> closingPrices = Lists.newArrayList(
                "20", "50", "20", "50", "55", "50", "10", "60", "60", "60", "20", "20", "20", "20", "20", "20", "20", "20", "20", "20"
        );
        List<String> upperBand = Lists.newArrayList(
                "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50"
        );
        List<String> movingAverage = Lists.newArrayList(
                "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30"
        );
        List<String> lowerBand = Lists.newArrayList(
                "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10"
        );
        testStrategy(closingPrices, upperBand, movingAverage, lowerBand, 55);
    }

    @Test
    public void test8() throws IOException {
        List<String> closingPrices = Lists.newArrayList(
                "20", "50", "20", "50", "55", "50", "10", "60", "60", "60", "20", "50", "20", "20", "20", "20", "20", "20", "20", "20"
        );
        List<String> upperBand = Lists.newArrayList(
                "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50", "50"
        );
        List<String> movingAverage = Lists.newArrayList(
                "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30"
        );
        List<String> lowerBand = Lists.newArrayList(
                "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10"
        );
        testStrategy(closingPrices, upperBand, movingAverage, lowerBand, 90);
    }
}
