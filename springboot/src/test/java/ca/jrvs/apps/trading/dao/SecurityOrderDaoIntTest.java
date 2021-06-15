package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Quote;
import ca.jrvs.apps.trading.model.domain.SecurityOrder;
import ca.jrvs.apps.trading.model.domain.Trader;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.assertj.core.util.Lists;
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
public class SecurityOrderDaoIntTest {

  @Autowired
  public SecurityOrderDao securityOrderDao;
  @Autowired
  private AccountDao accountDao;
  @Autowired
  private TraderDao traderDao;
  @Autowired
  private QuoteDao quoteDao;

  private SecurityOrder savedOrder;
  private Trader savedTrader;
  private Account savedAccount;
  private Quote savedQuote;

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

    savedTrader = new Trader();
    savedTrader.setFirstName("Tina");
    savedTrader.setLastName("Lee");
    savedTrader.setCountry("Canada");
    savedTrader.setEmail("tina.lee@gmail.com");
    savedTrader.setDob(new Date(System.currentTimeMillis()));
    traderDao.save(savedTrader);
    savedAccount = new Account();
    savedAccount.setAmount(10d);
    savedAccount.setTraderId(savedTrader.getId());
    accountDao.save(savedAccount);

    savedOrder = new SecurityOrder();
    savedOrder.setNotes("order created");
    savedOrder.setAccountId(savedAccount.getId());
    savedOrder.setSize(5);
    savedOrder.setPrice(15d);
    savedOrder.setStatus("SUCCESS");
    savedOrder.setTicker("AAPL");
    securityOrderDao.save(savedOrder);
  }

  @After
  public void deleteOne() {
    securityOrderDao.deleteById(savedOrder.getId());
    accountDao.deleteById(savedAccount.getId());
    traderDao.deleteById(savedTrader.getId());
    quoteDao.deleteById(savedQuote.getId());
  }

  @Test
  public void findAllById() {
    List<SecurityOrder> orders = Lists
        .newArrayList(securityOrderDao.findAllById(Arrays.asList(savedOrder.getId())));
    assertEquals(1, orders.size());
    assertEquals(orders.get(0).getTicker(), "AAPL");
    assertEquals(savedOrder.getAccountId(), orders.get(0).getAccountId());
  }
}