package com.bollinger.strategies;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bollinger.beans.BollingerState;

import static com.bollinger.strategies.util.ComparisonUtil.Band.LOWER_BAND;
import static com.bollinger.strategies.util.ComparisonUtil.Band.UPPER_BAND;
import static com.bollinger.strategies.util.ComparisonUtil.isCloseAbove;
import static com.bollinger.strategies.util.ComparisonUtil.isCloseAboveOrEqual;
import static com.bollinger.strategies.util.ComparisonUtil.isCloseBelowOrEqual;

/**
 * The inverse of {@link WalkUpBandStrategy}. If the prices touches / crosses the lower band often, do not invest.
 * The longer it hovers below the lower band, the less inclined we are to invest.
 * It takes a pretty strong price move to exceed this lower band.
 * An lower band touch that occurs over and over would signal the start of an downtrend.
 * Except if it then touches the upper band.
 *
 * @see <a href="http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:bollinger_bands#signalwalking_the_bands">Walking The Bands</a>
 */
public class WalkDownBandStrategy extends Strategy {

    private static final Logger logger = LoggerFactory.getLogger(WalkDownBandStrategy.class);

    @Override
    public int score(List<BollingerState> states) {

        int belowCounter = 0;
        int downCrossCounter = 0;

        BollingerState prev = null;

        // iterate chronologically from the earliest day
        for (int i = states.size() - 1; i >= 0; i--) {
            BollingerState iter = states.get(i);

            if (isCloseAboveOrEqual(UPPER_BAND, iter)) {
                belowCounter = 0;
                downCrossCounter = 0;
            }

            if (isCloseBelowOrEqual(LOWER_BAND, iter)) {
                belowCounter++;

                if (prev != null && isCloseAbove(LOWER_BAND, prev)) {
                    downCrossCounter++;
                }
            }

            prev = iter;
        }

        return belowCounter * -10 + downCrossCounter * -25;
    }

}
