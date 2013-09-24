package com.bollinger.strategies;

import java.util.List;

import com.bollinger.beans.BollingerState;

/**
 * Abstract strategy to calculate the score of a particular stock's opportunity for investment.
 */
public abstract class Strategy {
    /**
     * Calculate the score of a series of {@link BollingerState} according to its own strategy judgement.
     *
     * @param states
     *      Ordered by the date of the state, descendingly. (The newest is at {@code [0]}, the oldest is at the end of the list)
     * @return
     */
    public abstract int score(List<BollingerState> states);

    public String toString() {
        return this.getClass().getSimpleName();
    }
}
