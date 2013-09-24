package com.bollinger.beans;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.LocalDate;

public class BollingerState {
    private LocalDate date;
    private BigDecimal movingAverage;
    private BigDecimal standardDeviation;
    private BigDecimal upperBand;
    private BigDecimal lowerBand;
    private BigDecimal close;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getMovingAverage() {
        return movingAverage;
    }

    public void setMovingAverage(BigDecimal movingAverage) {
        this.movingAverage = movingAverage;
    }

    public BigDecimal getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(BigDecimal standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public BigDecimal getUpperBand() {
        return upperBand;
    }

    public void setUpperBand(BigDecimal upperBand) {
        this.upperBand = upperBand;
    }

    public BigDecimal getLowerBand() {
        return lowerBand;
    }

    public void setLowerBand(BigDecimal lowerBand) {
        this.lowerBand = lowerBand;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
