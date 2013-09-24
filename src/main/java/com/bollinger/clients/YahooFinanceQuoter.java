package com.bollinger.clients;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bollinger.beans.YahooQuote;

/**
 * A tool to quote Yahoo! for stock values.
 */
public class YahooFinanceQuoter {

    private static final Logger logger = LoggerFactory.getLogger(YahooFinanceQuoter.class);
    private final LocalDate date;

    public YahooFinanceQuoter(LocalDate date) {
        this.date = date;
    }

    /**
     * Start requesting Yahoo! for the stock values.
     *
     * @return Result is sorted by date (newer quotes are first).
     * @throws IOException
     */
    public List<YahooQuote> quote(String ticker) throws IOException {
        HttpResponse response = requestQuote(ticker);
        return parseQuote(response);
    }

    private HttpResponse requestQuote(String ticker) throws IOException {
        int toMonth = date.getMonthOfYear() - 1; // Yahoo API talks in zero indexed month.
        int toDay = date.getDayOfMonth();
        int toYear = date.getYear();

        LocalDate fromDate = date.minusWeeks(9);
        int fromMonth = fromDate.getMonthOfYear() - 1;
        int fromDay = fromDate.getDayOfMonth();
        int fromYear = fromDate.getYear();

        HttpClient hc = new DefaultHttpClient();
        String uri = "http://ichart.yahoo.com/table.csv?" +
                "s=" + ticker +
                "&a=" + fromMonth +
                "&b=" + fromDay + // inclusive
                "&c=" + fromYear +
                "&d=" + toMonth +
                "&e=" + toDay + // inclusive
                "&f=" + toYear;

        return hc.execute(new HttpGet(uri));
    }

    private List<YahooQuote> parseQuote(HttpResponse response) throws IOException {
        List<YahooQuote> quotes = new ArrayList<YahooQuote>();
        InputStreamReader in = new InputStreamReader(response.getEntity().getContent());
        CSVParser parser = new CSVParser(in);

        parser.getLine(); // Skip first CSV line (headers)
        String[] values = parser.getLine(); // Line 2

        // Collect every line
        while (values != null) {
            quotes.add(extract(values));
            values = parser.getLine();
        }

        return quotes;
    }

    private YahooQuote extract(String[] values) {
        YahooQuote q = new YahooQuote();

        q.setDate(LocalDate.parse(values[0]));
        q.setOpen(new BigDecimal(values[1]));
        q.setHigh(new BigDecimal(values[2]));
        q.setLow(new BigDecimal(values[3]));
        q.setClose(new BigDecimal(values[4]));
        q.setVolume(Long.valueOf(values[5]));
        q.setAdjustedClose(new BigDecimal(values[6]));

        return q;
    }

}
