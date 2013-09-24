package com.bollinger.beans;

import java.util.Map;

import com.beust.jcommander.internal.Maps;
import com.bollinger.strategies.Strategy;

public class AnalysisResult {
    private String ticker;
    private String pngUrl;
    private Map<Strategy, Integer> scores = Maps.newHashMap();

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getPngUrl() {
        return pngUrl;
    }

    public void setPngUrl(String pngUrl) {
        this.pngUrl = pngUrl;
    }

    public Map<Strategy, Integer> getScores() {
        return scores;
    }

    public void addScores(Map<Strategy, Integer> scores) {
        this.scores.putAll(scores);
    }

    public int calculateTotalScore() {
        int sum = 0;
        for (Integer score : scores.values()) {
            sum += score;
        }
        return sum;
    }
}
