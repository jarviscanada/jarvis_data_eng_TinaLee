package ca.jrvs.apps.trading.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.domain.TraderAccountView;
import java.util.Date;
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
public class TraderAccountServiceIntTest {

  private TraderAccountView savedView;
  private Trader trader;
  @Autowired
  private TraderAccountService traderAccountService;
  @Autowired
  private TraderDao traderDao;
  @Autowired
  private AccountDao accountDao;

  @Before
  public void setUp() throws Exception {
    trader = new Trader();
    trader.setFirstName("Tina");
    trader.setLastName("Lee");
    trader.setCountry("Canada");
    trader.setEmail("tina@gmail.com");
    trader.setDob(new Date(System.currentTimeMillis()));
    savedView = traderAccountService.createTraderAndAccount(trader);
    assertEquals(savedView.getTrader().getFirstName(), "Tina");
    Double expectedAmount = 0d;
    assertEquals(savedView.getAccount().getAmount(), expectedAmount);
  }

  @After
  public void delete() {
    int traderId = savedView.getTrader().getId();
    try {
      traderAccountService.deleteTraderById(traderId);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }
    traderAccountService.withdraw(traderId, 20d);
    traderAccountService.deleteTraderById(traderId);
  }

  @Test
  public void depositAndWithDraw() {
    int traderId = savedView.getTrader().getId();
    Account updatedAccount = traderAccountService.deposit(traderId, 50d);
    Double expectedAmount = 50d;
    assertEquals(updatedAccount.getAmount(), expectedAmount);
    updatedAccount = traderAccountService.withdraw(traderId, 30d);
    expectedAmount = 20d;
    assertEquals(updatedAccount.getAmount(), expectedAmount);
  }
}