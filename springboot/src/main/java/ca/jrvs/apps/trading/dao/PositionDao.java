package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Position;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PositionDao {

  private static final Logger logger = LoggerFactory.getLogger(PositionDao.class);

  private static final String VIEW_NAME = "position";
  private static final String ID_COLUMN = "account_id";

  private JdbcTemplate jdbcTemplate;

  @Autowired
  public PositionDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  /**
   * used to test position view
   */
  public List<Position> getPositions(Integer id) {
    String selectSql = "SELECT account_id, ticker, sum(size) AS position FROM security_order "
        + "WHERE status = 'FILLED' AND " + ID_COLUMN + "=?" + "GROUP BY account_id, ticker";
    List<Position> positions = jdbcTemplate.query(selectSql,
        BeanPropertyRowMapper.newInstance(Position.class), id);
    return positions;
  }

  public List<Position> findAllPositions(Integer id) {
    String selectSql = "SELECT * FROM " + VIEW_NAME + " WHERE " + ID_COLUMN +
        "=?";
    List<Position> positions = jdbcTemplate.query(selectSql,
        BeanPropertyRowMapper.newInstance(Position.class), id);
    return positions;
  }
}
