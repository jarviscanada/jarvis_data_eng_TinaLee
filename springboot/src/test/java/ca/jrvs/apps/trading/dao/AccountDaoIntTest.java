package ca.jrvs.apps.trading.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Account;
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
public class AccountDaoIntTest {

  @Autowired
  public AccountDao accountDao;
  @Autowired
  private TraderDao traderDao;

  private Trader savedTrader;
  private Account savedAccount;

  @Before
  public void insertOne() {
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
  }

  @After
  public void deleteOne() {
    accountDao.deleteById(savedAccount.getId());
    traderDao.deleteById(savedTrader.getId());
  }

  @Test
  public void findAllyById() {
    List<Account> accounts = Lists
        .newArrayList(accountDao.findAllById(Arrays.asList(savedAccount.getId())));
    assertEquals(1, accounts.size());
    assertEquals(savedAccount.getTraderId(), accounts.get(0).getTraderId());
  }
}