package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes =  {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteDaoIntTest {

  @Autowired
  private QuoteDao quoteDao;

  private Quote savedQuote;
  private Quote savedQuote1;

  @Before
  public void insertOne() {
    savedQuote = new Quote();
    savedQuote.setId("AAPL");
    savedQuote.setAskPrice(10d);
    savedQuote.setAskSize(10);
    savedQuote.setBidPrice(10.2d);
    savedQuote.setBidSize(10);
    savedQuote.setLastPrice(10.1d);
    quoteDao.save(savedQuote);
    savedQuote1 = new Quote();
    savedQuote1.setId("MSFT");
    savedQuote1.setAskPrice(10d);
    savedQuote1.setAskSize(10);
    savedQuote1.setBidPrice(10.2d);
    savedQuote1.setBidSize(10);
    savedQuote1.setLastPrice(10.1d);
    quoteDao.saveAll(Arrays.asList(savedQuote1));
  }

  @Test
  public void find() {
    Quote findQuote = quoteDao.findById(savedQuote.getId()).get();
    assertEquals(findQuote.getId(), savedQuote.getId());
    assertEquals(findQuote.getBidPrice(), savedQuote.getBidPrice());
    assertEquals(findQuote.getBidSize(), savedQuote.getBidSize());
    assertEquals(findQuote.getAskPrice(), savedQuote.getAskPrice());
    assertEquals(findQuote.getAskSize(), savedQuote.getAskSize());
    assertEquals(findQuote.getLastPrice(), savedQuote.getLastPrice());
  }

  @Test
  public void findAll() {
    Quote quote = quoteDao.findAll().get(0);
    assertEquals(quote.getAskPrice(), savedQuote.getAskPrice());
    assertEquals(quote.getAskSize(), savedQuote.getAskSize());
    assertEquals(quote.getBidPrice(), savedQuote.getBidPrice());
    assertEquals(quote.getBidSize(), savedQuote.getBidSize());
    assertEquals(quote.getTicker(), savedQuote.getTicker());
    quote = quoteDao.findAll().get(1);
    assertEquals(quote.getAskPrice(), savedQuote1.getAskPrice());
    assertEquals(quote.getAskSize(), savedQuote1.getAskSize());
    assertEquals(quote.getBidPrice(), savedQuote1.getBidPrice());
    assertEquals(quote.getBidSize(), savedQuote1.getBidSize());
    assertEquals(quote.getTicker(), savedQuote1.getTicker());
  }

  @After
  public void deleteOne() {
    quoteDao.deleteById(savedQuote.getId());
    quoteDao.deleteAll();
  }
}