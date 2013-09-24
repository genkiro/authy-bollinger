package com.bollinger.strategies;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import com.bollinger.beans.BollingerState;
import com.bollinger.chart.Painter;

public abstract class StrategyTest {

    protected String testName;

    protected abstract Strategy createStrategy();

    @BeforeMethod
    public void handleTestMethodName(Method method) {
        testName = method.getName();
    }

    /**
     * Feel free to overwrite.
     * @return true if we need to see the graphs.
     */
    protected boolean needToPaint() {
        return false;
    }

    protected void paint(List<BollingerState> cases) throws IOException {
        if (needToPaint()) {
            new Painter(testName, new LocalDate()).paint(cases);
        }
    }

    protected void testStrategy(List<String> closingPrices,
                                List<String> upperBand,
                                List<String> movingAverage,
                                List<String> lowerBand,
                                int expected)
            throws IOException
    {
        List<BollingerState> cases = createCases(closingPrices, upperBand, movingAverage, lowerBand);
        paint(cases);
        int result = createStrategy().score(cases);
        Assert.assertEquals(result, expected);
    }

    protected List<BollingerState> createCases(List<String> closingPrices,
                                               List<String> upperBand,
                                               List<String> movingAverage,
                                               List<String> lowerBand)
    {
        List<BollingerState> states = new ArrayList<BollingerState>();
        int size = closingPrices.size();

        for (int i = 0; i < size; i++) {
            BollingerState bs = new BollingerState();
            bs.setDate(new LocalDate());
            bs.setClose(new BigDecimal(closingPrices.get(i)));
            bs.setUpperBand(new BigDecimal(upperBand.get(i)));
            bs.setMovingAverage(new BigDecimal(movingAverage.get(i)));
            bs.setLowerBand(new BigDecimal(lowerBand.get(i)));

            states.add(0, bs);
        }

        return states;
    }

    protected BollingerState createState(String date, String movingAvg, String stdDev, String upperBand, String lowerBand, String close) {
        BollingerState bs = new BollingerState();
        bs.setDate(LocalDate.parse(date));
        bs.setMovingAverage(new BigDecimal(movingAvg));
        bs.setStandardDeviation(new BigDecimal(stdDev));
        bs.setUpperBand(new BigDecimal(upperBand));
        bs.setLowerBand(new BigDecimal(lowerBand));
        bs.setClose(new BigDecimal(close));
        return bs;
    }

    @AfterTest
    public void infLoopSoThatGraphsWontClose() {
        if (needToPaint()) {
            while (true) {}
        }
    }
}
