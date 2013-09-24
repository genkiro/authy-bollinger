package com.bollinger.extractor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bollinger.beans.BollingerState;
import com.bollinger.beans.YahooQuote;

/**
 * A simple utility class to extract the {@link BollingerState}s out of a sequence of stock quotes.
 */
public class BollingerExtractor {

    private static final Logger logger = LoggerFactory.getLogger(BollingerExtractor.class);

    /**
     * Given the quotes from Yahoo, compose the sequence of {@link BollingerState}s out of them. The input's order is
     * kept in the output.
     *
     * @param quotes {@link YahooQuote}s to convert.
     * @return
     *      A sequence of the {@link BollingerState}s.
     */
    public static List<BollingerState> extract(List<YahooQuote> quotes) {
        List<BollingerState> states = new ArrayList<BollingerState>();

        for (int i = 0; i < 20; i++) {
            BollingerState state = new BollingerState();

            List<YahooQuote> twentyQuotes = quotes.subList(i, i + 20);

            BigDecimal avg = average(twentyQuotes);
            BigDecimal stdDev = calcStdDev(avg, twentyQuotes);
            BigDecimal bandRange = stdDev.multiply(new BigDecimal(2));

            YahooQuote quote = quotes.get(i);

            state.setDate(quote.getDate());
            state.setMovingAverage(avg);
            state.setStandardDeviation(stdDev);
            state.setUpperBand(avg.add(bandRange));
            state.setLowerBand(avg.subtract(bandRange));
            state.setClose(quote.getClose());

            logger.info("state: {}", state);
            states.add(state);
        }

        return states;
    }

    private static BigDecimal average(List<YahooQuote> quotes) {
        BigDecimal sum = BigDecimal.ZERO;
        for (YahooQuote q : quotes) {
            sum = sum.add(q.getClose());
        }

        return sum.divide(BigDecimal.valueOf(quotes.size()));
    }

    private static BigDecimal calcStdDev(BigDecimal average, List<YahooQuote> quotes) {
        BigDecimal sum = BigDecimal.ZERO;
        for (YahooQuote q : quotes) {
            BigDecimal sqDiff = q.getClose().subtract(average).pow(2);
            sum = sum.add(sqDiff);
        }

        BigDecimal stdDiffSq = sum.divide(BigDecimal.valueOf(quotes.size()));
        double stdDiff = Math.sqrt(stdDiffSq.floatValue());

        return BigDecimal.valueOf(stdDiff);
    }
}
