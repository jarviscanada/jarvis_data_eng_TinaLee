package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Account;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDao extends JdbcCrudDao<Account> {

  private static final Logger logger = LoggerFactory.getLogger(AccountDao.class);

  private final String TABLE_NAME = "account";
  private final String ID_COLUMN = "id";
  private static final String DEPOSIT_ACTION = "deposit";
  private static final String WITHDRAW_ACTION = "withdraw";

  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleInsert;

  @Autowired
  public AccountDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.simpleInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME)
        .usingGeneratedKeyColumns(ID_COLUMN);
  }

  @Override
  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  @Override
  public SimpleJdbcInsert getSimpleJdbcInsert() {
    return simpleInsert;
  }

  @Override
  public String getTableName() {
    return TABLE_NAME;
  }

  @Override
  public String getIdColumnName() {
    return ID_COLUMN;
  }

  @Override
  Class<Account> getEntityClass() {
    return Account.class;
  }

  @Override
  public int updateOne(Account entity) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public <S extends Account> Iterable<S> saveAll(Iterable<S> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void delete(Account account) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll(Iterable<? extends Account> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }

  public Account findByTraderId(Integer traderId) {
    String selectSql = "SELECT * FROM " + getTableName() + " WHERE trader_id=?";
    Account account = null;
    try {
      account = getJdbcTemplate().queryForObject(selectSql,
          BeanPropertyRowMapper.newInstance(getEntityClass()), traderId);
    } catch (IncorrectResultSizeDataAccessException e) {
      logger.debug("Cannot find account associated with trader id: " + traderId);
    }
    return account;
  }

  public Account updateAmountById(Integer traderId, Double fund, String action) {
    Account account = findByTraderId(traderId);
    Double newAmount;
    if (action.equals(DEPOSIT_ACTION)) {
      newAmount = account.getAmount() + fund;
    } else if (action.equals(WITHDRAW_ACTION)){
      newAmount = account.getAmount() - fund;
    } else {
      throw new IllegalArgumentException("Illegal action: Only deposit or withdraw");
    }

    String updateSql = "UPDATE " + getTableName() + " SET amount=? WHERE " +
        getIdColumnName() + "=?";
    jdbcTemplate.update(updateSql, newAmount, traderId);
    return findByTraderId(traderId);
  }
}
