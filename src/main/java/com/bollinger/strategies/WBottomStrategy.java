package com.bollinger.strategies;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bollinger.beans.BollingerState;

import static com.bollinger.strategies.util.ComparisonUtil.Band.LOWER_BAND;
import static com.bollinger.strategies.util.ComparisonUtil.isCloseAbove;
import static com.bollinger.strategies.util.ComparisonUtil.isCloseBelowOrEqual;

/**
 * When there is a W-shaped bottom with criteria:
 * <ul>
 *   <li>Two low points.</li>
 *   <li>Second low is lower than the first.</li>
 *   <li>First low is close or below the lower band.</li>
 *   <li>Middle bounce towards the middle band.</li>
 *   <li>Second low is above the lower band.</li>
 *   <li>Lastly, followed by a upsurge higher than the middle bounce.</li>
 * </ul>
 *
 * The fact that the second low is able to hold above the lower band on the test shows less weakness on the last decline.
 *
 * @see <a href="http://stockcharts.com/school/doku.php?id=chart_school:technical_indicators:bollinger_bands#signalw-bottoms">W bottom</a>
 */
public class WBottomStrategy extends Strategy {

    private static final Logger logger = LoggerFactory.getLogger(WBottomStrategy.class);

    @Override
    public int score(List<BollingerState> states) {

        Integer candidate = null;
        int size = states.size();

        // Find start of bounce candidate
        for (int i = size - 2; i >= 0; i--) {
            BollingerState iter = states.get(i);

            if (isCloseBelowOrEqual(LOWER_BAND, iter)) {
                candidate = i;
           }
        }

        if (candidate == null) {
            return 0;
        }

        // Setup criteria
        boolean bounceBack = false;
        boolean secondDip = false;
        boolean secondDipDidNotTouchLowerBand = true;
        boolean breakout = false;

        BigDecimal firstDipValue = new BigDecimal("9999999");
        BigDecimal topBounceValue = BigDecimal.ZERO;

        for (int i = candidate; i >= 0; i--) {
            BollingerState iter = states.get(i);
            BigDecimal close = iter.getClose();

            if (!bounceBack) {
                if (firstDipValue.compareTo(close) == 1) { // 1dip > close
                    firstDipValue = close;
                }

                if (isCloseAbove(LOWER_BAND, iter)) {
                    bounceBack = true;
                    topBounceValue = close;
                }
                continue;
            }

            if (!secondDip) {
                if (topBounceValue.compareTo(close) == -1) { // topBounce < close
                    topBounceValue = close;
                }

                if (firstDipValue.compareTo(close) == 1 && isCloseAbove(LOWER_BAND, iter)) { // 1dip > close && close above lower_band
                    secondDip = true;
                }
                continue;
            }

            if (isCloseBelowOrEqual(LOWER_BAND, iter)) {
                secondDipDidNotTouchLowerBand = false;
            }

            if (!breakout) {
                if (topBounceValue.compareTo(close) == -1) { // topBounce < close
                    breakout = true;
                }
                continue;
            }

        }

        return (bounceBack && secondDip && secondDipDidNotTouchLowerBand && breakout) ? 100 : 0;
    }

}
