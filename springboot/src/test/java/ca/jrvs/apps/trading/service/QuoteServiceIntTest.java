package ca.jrvs.apps.trading.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteServiceIntTest {

  @Autowired
  private QuoteService quoteService;

  @Autowired
  private QuoteDao quoteDao;

  private Quote savedQuote;

  @Before
  public void setUp() throws Exception {
    quoteDao.deleteAll();
    savedQuote = new Quote();
    savedQuote.setId("AAPL");
    savedQuote.setAskPrice(10d);
    savedQuote.setAskSize(10);
    savedQuote.setBidPrice(10.2d);
    savedQuote.setBidSize(10);
    savedQuote.setLastPrice(10.1d);
    quoteService.saveQuote(savedQuote);
  }

  @Test
  public void findIexQuoteByTicker() {
    assertEquals(quoteService.findIexQuoteByTicker("AAPL").getSymbol(), "AAPL");
  }

  @Test
  public void updateMarketData() {
    List<Quote> quotes = quoteService.findAllQuotes();
    quotes.forEach(System.out::println);
    quoteService.updateMarketData();
    quotes = quoteService.findAllQuotes();
    quotes.forEach(System.out::println);
  }

  @Test
  public void saveQuotes() {
    assertEquals(quoteService.saveQuotes(Arrays.asList("FB", "AAPL")).size(), 2);
  }

  @Test
  public void saveQuote() {
    savedQuote = new Quote();
    savedQuote.setId("MSFT");
    savedQuote.setAskPrice(10d);
    savedQuote.setAskSize(10);
    savedQuote.setBidPrice(10.2d);
    savedQuote.setBidSize(10);
    savedQuote.setLastPrice(10.1d);
    quoteService.saveQuote(savedQuote);
    assertEquals(quoteService.findAllQuotes().size(), 2);
  }

  @Test
  public void findAllQuotes() {
    assertEquals(quoteService.findAllQuotes().size(), 1);
  }

  @After
  public void delete() {
    quoteDao.deleteAll();
  }
}