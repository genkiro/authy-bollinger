package com.bollinger.strategies;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.bollinger.beans.BollingerState;
import com.google.common.collect.Lists;

public class WBottomStrategyTest extends StrategyTest {

    @Override
    public Strategy createStrategy() {
        return new WBottomStrategy();
    }

    @Test
    public void test__RealWorldWBottom() throws IOException {
        List<BollingerState> cases = createRealWorldWBottom();
        paint(cases);
        int result = createStrategy().score(cases);
        Assert.assertEquals(100, result);
    }

    @Test
    public void testNotW1() throws IOException {
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
    public void testNotW2() throws IOException {
        List<String> closingPrices = Lists.newArrayList(
            "20", "10",  "5",  "5",  "5",  "5",  "5",  "5",  "5",  "5",  "5",  "5",  "5",  "5",  "5",  "5",  "5",  "5",  "5",  "5"
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
    public void testNotW3() throws IOException {
        List<String> closingPrices = Lists.newArrayList(
            "20", "10",  "5", "23", "23", "23", "23", "23", "23", "23", "23", "23", "23", "23", "23", "23", "23", "23", "23", "23"
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
    public void testNotW4() throws IOException {
        List<String> closingPrices = Lists.newArrayList(
            "20", "10",  "5", "23", "25", "24", "27", "27", "23", "23", "23", "23", "23", "23", "23", "23", "23", "23", "23", "23"
        );
        List<String> upperBand = Lists.newArrayList(
            "50", "50", "50", "50", "50", "50", "50", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59"
        );
        List<String> movingAverage = Lists.newArrayList(
            "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30"
        );
        List<String> lowerBand = Lists.newArrayList(
            "10", "10", "10", "10", "10", "10", "10", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1"
        );
        testStrategy(closingPrices, upperBand, movingAverage, lowerBand, 0);
    }

    @Test
    public void testNotW5() throws IOException {
        List<String> closingPrices = Lists.newArrayList(
            "20", "10",  "5", "23", "25", "24", "27",  "3", "23", "23", "23", "23", "23", "23", "23", "23", "23", "23", "23", "23"
        );
        List<String> upperBand = Lists.newArrayList(
            "50", "50", "50", "50", "50", "50", "50", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59"
        );
        List<String> movingAverage = Lists.newArrayList(
            "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30"
        );
        List<String> lowerBand = Lists.newArrayList(
            "10", "10", "10", "10", "10", "10", "10",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1"
        );
        testStrategy(closingPrices, upperBand, movingAverage, lowerBand, 0);
    }

    @Test
    public void testNotW6() throws IOException {
        List<String> closingPrices = Lists.newArrayList(
            "20", "10",  "5", "23", "25", "24", "27",  "3", "26", "23", "23", "23", "23", "23", "23", "23", "23", "23", "23", "23"
        );
        List<String> upperBand = Lists.newArrayList(
            "50", "50", "50", "50", "50", "50", "50", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59"
        );
        List<String> movingAverage = Lists.newArrayList(
            "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30"
        );
        List<String> lowerBand = Lists.newArrayList(
            "10", "10", "10", "10", "10", "10", "10",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1"
        );
        testStrategy(closingPrices, upperBand, movingAverage, lowerBand, 0);
    }

    @Test
    public void testNotW7() throws IOException {
        // breakout does not exceed topBounceValue
        List<String> closingPrices = Lists.newArrayList(
            "20", "10",  "5", "23", "25", "24", "27",  "3", "26", "27", "23", "23", "23", "23", "23", "23", "23", "23", "23", "23"
        );
        List<String> upperBand = Lists.newArrayList(
            "50", "50", "50", "50", "50", "50", "50", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59"
        );
        List<String> movingAverage = Lists.newArrayList(
            "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30"
        );
        List<String> lowerBand = Lists.newArrayList(
            "10", "10", "10", "10", "10", "10", "10",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1"
        );
        testStrategy(closingPrices, upperBand, movingAverage, lowerBand, 0);
    }

    @Test
    public void testW() throws IOException {
        List<String> closingPrices = Lists.newArrayList(
            "20", "10",  "5", "23", "25", "24", "27",  "3", "26", "27", "28", "23", "23", "23", "23", "23", "23", "23", "23", "23"
        );
        List<String> upperBand = Lists.newArrayList(
            "50", "50", "50", "50", "50", "50", "50", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59"
        );
        List<String> movingAverage = Lists.newArrayList(
            "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30"
        );
        List<String> lowerBand = Lists.newArrayList(
            "10", "10", "10", "10", "10", "10", "10",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1"
        );
        testStrategy(closingPrices, upperBand, movingAverage, lowerBand, 100);
    }

    @Test
    public void test_Tweak1() throws IOException {
        // second dip touches lower band
        List<String> closingPrices = Lists.newArrayList(
            "20", "10",  "5", "23", "25", "24", "27",  "0", "26", "27", "28", "23", "23", "23", "23", "23", "23", "23", "23", "23"
        );
        List<String> upperBand = Lists.newArrayList(
            "50", "50", "50", "50", "50", "50", "50", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59"
        );
        List<String> movingAverage = Lists.newArrayList(
            "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30"
        );
        List<String> lowerBand = Lists.newArrayList(
            "10", "10", "10", "10", "10", "10", "10",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1"
        );
        testStrategy(closingPrices, upperBand, movingAverage, lowerBand, 0);
    }

    @Test
    public void test_Tweak2() throws IOException {
        // second dip is not deep enough
        List<String> closingPrices = Lists.newArrayList(
            "20", "10",  "5", "23", "25", "24", "27",  "10", "26", "27", "28", "23", "23", "23", "23", "23", "23", "23", "23", "23"
        );
        List<String> upperBand = Lists.newArrayList(
            "50", "50", "50", "50", "50", "50", "50", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59", "59"
        );
        List<String> movingAverage = Lists.newArrayList(
            "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30"
        );
        List<String> lowerBand = Lists.newArrayList(
            "10", "10", "10", "10", "10", "10", "10",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1",  "1"
        );
        testStrategy(closingPrices, upperBand, movingAverage, lowerBand, 0);
    }

    @Test
    public void test_WW() throws IOException {
        List<String> closingPrices = Lists.newArrayList(
            "20", "10", "9", "23", "25", "24", "27", "8", "26", "27", "28", "4", "7", "3", "8", "8", "8", "8", "8", "8"
        );
        List<String> upperBand = Lists.newArrayList(
            "50", "50", "50", "50", "50", "50", "50", "55", "55", "55", "55", "55", "55", "59", "59", "59", "59", "59", "59", "59"
        );
        List<String> movingAverage = Lists.newArrayList(
            "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30", "30"
        );
        List<String> lowerBand = Lists.newArrayList(
            "10", "10", "10", "10", "10", "10", "10", "5", "5", "5", "5", "5", "5", "1", "1", "1", "1", "1", "1", "1"
        );
        testStrategy(closingPrices, upperBand, movingAverage, lowerBand, 100);
    }

    private List<BollingerState> createRealWorldWBottom() {
        List<BollingerState> states = new ArrayList<BollingerState>();

        // from http://ichart.yahoo.com/table.csv?s=GOOG&a=6&b=11&c=2013&d=8&e=12&f=2013

        states.add(createState("2013-09-12", "869.2715", "14.327037108543076", "897.925574217086152", "840.617425782913848", "893.06"));
        states.add(createState("2013-09-11", "868.109",  "13.252639219757794", "894.614278439515588", "841.603721560484412", "896.19"));
        states.add(createState("2013-09-10", "867.362",  "12.011727642273131", "891.385455284546262", "843.338544715453738", "888.67"));
        states.add(createState("2013-09-09", "867.204",  "11.748301931198464", "890.700603862396928", "843.707396137603072", "888.05"));
        states.add(createState("2013-09-06", "867.322",  "11.966904460559718", "891.255808921119436", "843.388191078880564", "879.58"));
        states.add(createState("2013-09-05", "867.976",  "12.93703470913755", "893.85006941827510",  "842.10193058172490",  "879.56"));
        states.add(createState("2013-09-04", "868.5305", "13.640215016569284", "895.810930033138568", "841.250069966861432", "871.63"));
        states.add(createState("2013-09-03", "869.7775", "14.944256264133251", "899.666012528266502", "839.888987471733498", "860.38"));
        states.add(createState("2013-08-30", "872.0085", "16.612314532918795", "905.233129065837590", "838.783870934162410", "846.90"));
        states.add(createState("2013-08-29", "874.992",  "17.183444123723422", "909.358888247446844", "840.625111752553156", "855.43"));
        states.add(createState("2013-08-28", "877.4315", "17.688974271873867", "912.809448543747734", "842.053551456252266", "848.55"));
        states.add(createState("2013-08-27", "879.3915", "16.51286507953013",  "912.41723015906026",  "846.36576984093974",  "850.15"));
        states.add(createState("2013-08-26", "881.43",  "15.24504133810042",  "911.92008267620084",  "850.93991732379916",  "866.39"));
        states.add(createState("2013-08-23", "882.224",  "14.849446378207432", "911.922892756414864", "852.525107243585136", "870.21"));
        states.add(createState("2013-08-22", "882.981",  "14.601533762177445", "912.184067524354890", "853.777932475645110", "873.71"));
        states.add(createState("2013-08-21", "883.6805", "14.475198515747978", "912.630897031495956", "854.730102968504044", "869.33"));
        states.add(createState("2013-08-20", "885.359",  "14.659011287889664", "914.677022575779328", "856.040977424220672", "865.42"));
        states.add(createState("2013-08-19", "887.278",  "14.433620179272149", "916.145240358544298", "858.410759641455702", "865.65"));
        states.add(createState("2013-08-16", "889.5305", "14.397799514487449", "918.326099028974898", "860.734900971025102", "856.91"));
        states.add(createState("2013-08-15", "891.515",  "12.355258801172289", "916.225517602344578", "866.804482397655422", "859.66"));

        return states;
    }
}

