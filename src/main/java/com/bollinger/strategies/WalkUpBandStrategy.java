package com.bollinger.strategies;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bollinger.beans.BollingerState;

import static com.bollinger.strategies.util.ComparisonUtil.Band.LOWER_BAND;
import static com.bollinger.strategies.util.ComparisonUtil.Band.UPPER_BAND;
import static com.bollinger.strategies.util.ComparisonUtil.isCloseAboveOrEqual;
import static com.bollinger.strategies.util.ComparisonUtil.isCloseBelow;
import static com.bollinger.strategies.util.ComparisonUtil.isCloseBelowOrEqual;

/**
 * If the prices touches / crosses the upper band often, invest. The longer it hovers above the upper band,
 * the more inclined we are to invest. It takes a pretty strong price move to exceed this upper band.
 * An upper band touch that occurs over and over would signal the start of an uptrend.
 * Except if it then touches the lower band.
 *
 * @see <a href="http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:bollinger_bands#signalwalking_the_bands">Walking The Bands</a>
 */
public class WalkUpBandStrategy extends Strategy {

    private static final Logger logger = LoggerFactory.getLogger(WalkUpBandStrategy.class);

    @Override
    public int score(List<BollingerState> states) {

        int aboveCounter = 0;
        int upCrossCounter = 0;

        BollingerState prev = null;

        // iterate chronologically from the earliest day
        for (int i = states.size() - 1; i >= 0; i--) {
            BollingerState iter = states.get(i);

            if (isCloseBelowOrEqual(LOWER_BAND, iter)) {
                aboveCounter = 0;
                upCrossCounter = 0;
            }

            if (isCloseAboveOrEqual(UPPER_BAND, iter)) {
                aboveCounter++;

                if (prev != null && isCloseBelow(UPPER_BAND, prev)) {
                    upCrossCounter++;
                }
            }

            prev = iter;
        }

        return aboveCounter * 10 + upCrossCounter * 25;
    }

}
