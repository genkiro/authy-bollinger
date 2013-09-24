package com.bollinger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.internal.Maps;
import com.bollinger.beans.AnalysisResult;
import com.bollinger.beans.BollingerState;
import com.bollinger.beans.YahooQuote;
import com.bollinger.chart.Painter;
import com.bollinger.clients.YahooFinanceQuoter;
import com.bollinger.extractor.BollingerExtractor;
import com.bollinger.scorer.BollingerScorer;
import com.bollinger.strategies.Strategy;
import com.bollinger.strategies.WBottomStrategy;
import com.bollinger.strategies.WalkDownBandStrategy;
import com.bollinger.strategies.WalkUpBandStrategy;
import com.google.common.base.Functions;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

public class BollingerMain {

    private static final Logger logger = LoggerFactory.getLogger(BollingerMain.class);

    public static void main(String[] args) throws Exception {
        if (0 == args.length) {
            logger.error("Example usages:");
            logger.error("mvn exec:java -Dexec.args=\"GOOG\"");
            logger.error("mvn exec:java -Dexec.args=\"2013-09-12 GOOG AAPL DATA\"");
        }

        List<String> tickers = Lists.newArrayList(args);

        LocalDate baseDate = new LocalDate(); // Today

        try {
            baseDate = LocalDate.parse(args[0]); // Replace if date is specified
            tickers.remove(0);
        } catch (IllegalArgumentException ex) {
            // first arg is ticker. no-op.
        }


        YahooFinanceQuoter quoter = new YahooFinanceQuoter(baseDate);
        List<AnalysisResult> results = new ArrayList<AnalysisResult>();

        for (String ticker : tickers) {
            List<YahooQuote> quotes = quoter.quote(ticker);
            List<BollingerState> states = BollingerExtractor.extract(quotes);

            AnalysisResult result = new AnalysisResult();
            result.setTicker(ticker);

            Painter painter = new Painter(ticker, baseDate);
            result.setPngUrl(painter.paint(states));

            Map<Strategy, Integer> scores =
                    new BollingerScorer()
                    .addStrategy(new WBottomStrategy())
                    .addStrategy(new WalkUpBandStrategy())
                    .addStrategy(new WalkDownBandStrategy())
                    .score(states);
            result.addScores(scores);

            results.add(result);
        }

        displayResults(results);
    }

    public static void displayResults(List<AnalysisResult> results) {
        logger.info("====================================================");
        logger.info("=                      RESULT                      =");
        logger.info("====================================================");

        Map<String, Integer> totalScoreMap = Maps.newHashMap();

        for (AnalysisResult result : results) {
            int totalScore = result.calculateTotalScore();
            String ticker = result.getTicker();
            totalScoreMap.put(ticker, totalScore);

            logger.info("");
            logger.info("Ticker: {}", ticker);
            logger.info("png: {}", result.getPngUrl());
            logger.info("Scores: {}", result.getScores());
            logger.info("Total Score: {}", totalScore);
        }

        Ordering<String> valComparator = Ordering.natural().reverse().onResultOf(Functions.forMap(totalScoreMap));
        ImmutableSortedMap<String, Integer> sorted = ImmutableSortedMap.copyOf(totalScoreMap, valComparator);

        logger.info("");
        logger.info("Below are the tickers ranked in our recommendation along with their scores (top is best):");

        int i = 1;
        for (String key : sorted.keySet()) {
            logger.info("{}. {}\t{}", i++, key, totalScoreMap.get(key));
        }

        logger.info("");
        logger.info("So, we should invest in {}", sorted.firstKey());
        logger.info("====================================================");
        logger.info("Press ctrl + C to terminate.");
    }
}
