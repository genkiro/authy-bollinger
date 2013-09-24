package com.bollinger.chart;

import java.awt.BorderLayout;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bollinger.beans.BollingerState;
import com.googlecode.charts4j.AxisLabels;
import com.googlecode.charts4j.AxisLabelsFactory;
import com.googlecode.charts4j.AxisStyle;
import com.googlecode.charts4j.AxisTextAlignment;
import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.DataUtil;
import com.googlecode.charts4j.Fills;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.Line;
import com.googlecode.charts4j.LineChart;
import com.googlecode.charts4j.LineStyle;
import com.googlecode.charts4j.LinearGradientFill;
import com.googlecode.charts4j.Plots;
import com.googlecode.charts4j.Shape;

import static com.googlecode.charts4j.Color.SKYBLUE;
import static com.googlecode.charts4j.Color.WHITE;

/**
 * A class for drawing charts.
 */
public class Painter {

    private static final Logger logger = LoggerFactory.getLogger(Painter.class);
    private final String ticker;
    private final LocalDate date;

    public Painter(String ticker, LocalDate date) {
        this.ticker = ticker;
        this.date = date;
    }

    public String paint(List<BollingerState> states) throws IOException {
        List<Double> movingAverages = new ArrayList<Double>();
        List<Double> upperBands = new ArrayList<Double>();
        List<Double> lowerBands = new ArrayList<Double>();
        List<Double> closePrices = new ArrayList<Double>();

        BigDecimal maxPoint = BigDecimal.ZERO;
        BigDecimal minPoint = BigDecimal.valueOf(9999999);

        for (BollingerState state : states) {
            movingAverages.add(0, state.getMovingAverage().doubleValue());
            upperBands.add(0, state.getUpperBand().doubleValue());
            lowerBands.add(0, state.getLowerBand().doubleValue());
            closePrices.add(0, state.getClose().doubleValue());

            if (maxPoint.compareTo(state.getClose()) == -1) {
                maxPoint = state.getClose();
            }
            if (maxPoint.compareTo(state.getUpperBand()) == -1) {
                maxPoint = state.getUpperBand();
            }
            if (minPoint.compareTo(state.getClose()) == 1) {
                minPoint = state.getClose();
            }
            if (minPoint.compareTo(state.getLowerBand()) == 1) {
                minPoint = state.getLowerBand();
            }
        }

        Integer max = maxPoint.add(BigDecimal.TEN).intValue();
        Integer mid = maxPoint.add(minPoint).divide(new BigDecimal(2)).intValue();
        Integer min = minPoint.subtract(BigDecimal.TEN).intValue();

        // Defining lines.
        Line line1 = Plots.newLine(DataUtil.scaleWithinRange(min, max, closePrices), Color.newColor("CA3D05"), "Close Prices");
        line1.setLineStyle(LineStyle.newLineStyle(3, 1, 0));
        line1.addShapeMarkers(Shape.DIAMOND, Color.newColor("CA3D05"), 12);
        line1.addShapeMarkers(Shape.DIAMOND, Color.WHITE, 8);

        Line line2 = Plots.newLine(DataUtil.scaleWithinRange(min, max, upperBands), SKYBLUE, "Upper Band");
        line2.setLineStyle(LineStyle.newLineStyle(3, 1, 0));
        line2.addShapeMarkers(Shape.DIAMOND, SKYBLUE, 12);
        line2.addShapeMarkers(Shape.DIAMOND, Color.WHITE, 8);

        Line line3 = Plots.newLine(DataUtil.scaleWithinRange(min, max, movingAverages), SKYBLUE, "Moving Average");
        line3.setLineStyle(LineStyle.newLineStyle(3, 1, 0));
        line3.addShapeMarkers(Shape.DIAMOND, SKYBLUE, 12);
        line3.addShapeMarkers(Shape.DIAMOND, Color.WHITE, 8);

        Line line4 = Plots.newLine(DataUtil.scaleWithinRange(min, max, lowerBands), SKYBLUE, "Lower Band");
        line4.setLineStyle(LineStyle.newLineStyle(3, 1, 0));
        line4.addShapeMarkers(Shape.DIAMOND, SKYBLUE, 12);
        line4.addShapeMarkers(Shape.DIAMOND, Color.WHITE, 8);

        // Defining chart.
        LineChart chart = GCharts.newLineChart(line1, line2, line3, line4);
        chart.setSize(600, 500);
        chart.setTitle("Bollinger Band for " + ticker + " at " + date, WHITE, 14);
        chart.setGrid(25, 25, 3, 2);

        AxisStyle axisStyle = AxisStyle.newAxisStyle(WHITE, 12, AxisTextAlignment.CENTER);

        // X axis
        AxisLabels xAxis = AxisLabelsFactory.newAxisLabels(createXLabels(states));
        xAxis.setAxisStyle(axisStyle);
        AxisLabels xAxis2 = AxisLabelsFactory.newAxisLabels("Day", 50.0);
        xAxis2.setAxisStyle(axisStyle);

        // Y axis
        AxisLabels yAxis = AxisLabelsFactory.newAxisLabels(min.toString(), mid.toString(), max.toString());
        yAxis.setAxisStyle(axisStyle);
        AxisLabels yAxis2 = AxisLabelsFactory.newAxisLabels("$", 50.0);
        yAxis2.setAxisStyle(axisStyle);

        // Adding axis info to chart.
        chart.addXAxisLabels(xAxis);
        chart.addXAxisLabels(xAxis2);
        chart.addYAxisLabels(yAxis);
        chart.addYAxisLabels(yAxis2);

        // Defining background and chart fills.
        chart.setBackgroundFill(Fills.newSolidFill(Color.newColor("1F1D1D")));
        LinearGradientFill fill = Fills.newLinearGradientFill(0, Color.newColor("363433"), 100);
        fill.addColorAndOffset(Color.newColor("2E2B2A"), 0);
        chart.setAreaFill(fill);
        String url = chart.toURLString();

        logger.info("Created chart: {}", url);
        displayUrlString(url);
        return url;
    }

    private List<String> createXLabels(List<BollingerState> states) {
        List<String> xLabels = new ArrayList<String>();
        for (BollingerState state : states) {
            xLabels.add(0, String.valueOf(state.getDate().getDayOfMonth()));
        }
        return xLabels;
    }

    private void displayUrlString(final String urlString) throws IOException {
        JFrame frame = new JFrame();
        JLabel label = new JLabel(new ImageIcon(ImageIO.read(new URL(urlString))));
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
