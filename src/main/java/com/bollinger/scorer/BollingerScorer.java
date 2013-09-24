package com.bollinger.scorer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.beust.jcommander.internal.Maps;
import com.bollinger.beans.BollingerState;
import com.bollinger.strategies.Strategy;

/**
 * To manage the strategies and calculate the score for each strategy. It involves normalization to make sure we get the
 * stock which bottom line effects are the best (not only that the stock is going up,
 * it would also give us the bigger gains per dollar invested).
 */
public class BollingerScorer {

    private final List<Strategy> strategies = new ArrayList<Strategy>();

    public BollingerScorer addStrategy(Strategy strategy) {
        strategies.add(strategy);
        return this;
    }

    public Map<Strategy, Integer> score(List<BollingerState> states) {
        Map<Strategy, Integer> map = Maps.newHashMap();
        for (Strategy s : strategies) {
            int score = s.score(states);
            map.put(s, score * normalizationScale(states));
        }
        return map;
    }

    private int normalizationScale(List<BollingerState> states) {
        BigDecimal sum = BigDecimal.ZERO;

        for (BollingerState state : states) {
            BigDecimal pseudoBandwidth = state.getStandardDeviation().divide(state.getMovingAverage(), RoundingMode.HALF_UP);
            sum = sum.add(pseudoBandwidth);
        }

        return sum.multiply(new BigDecimal(1000000)).intValue() / states.size();
    }
}
