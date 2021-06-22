package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public abstract class JdbcCrudDao<T extends Entity<Integer>> implements CrudRepository<T, Integer> {

  private static final Logger logger = LoggerFactory.getLogger(JdbcCrudDao.class);

  abstract public JdbcTemplate getJdbcTemplate();
  abstract public SimpleJdbcInsert getSimpleJdbcInsert();
  abstract public String getTableName();
  abstract public String getIdColumnName();
  abstract Class<T> getEntityClass();

  /**
   * Save an entity and update auto-generated integer ID
   * @param entity to be saved
   * @return saved entity
   */
  @Override
  public <S extends  T> S save(S entity) {
    if (existsById(entity.getId())) {
      if (updateOne(entity) != 1) {
        throw new DataRetrievalFailureException("Unable to update quote");
      }
    } else {
      addOne(entity);
    }
    return entity;
  }

  /**
   * helper method that saves one entity
   */
  private <S extends T> void addOne(S entity) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(entity);

    Number newId = getSimpleJdbcInsert().executeAndReturnKey(parameterSource);
    entity.setId(newId.intValue());
  }

  /**
   * helper method that updates one entity
   */
  abstract public int updateOne(T entity);

  @Override
  public Optional<T> findById(Integer id) {
    Optional<T> entity = Optional.empty();
    String selectSql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + "=?";

    try {
      entity = Optional.ofNullable((T) getJdbcTemplate()
          .queryForObject(selectSql,
              BeanPropertyRowMapper.newInstance(getEntityClass()), id));
    } catch (IncorrectResultSizeDataAccessException e) {
      logger.debug("Cannot find trader id: " + id, e);
    }
    return entity;
  }

  @Override
  public boolean existsById(Integer id) {
    String selectSql = "SELECT count(*) FROM " + getTableName() + " WHERE " + getIdColumnName() +
        "=?";
    boolean flag = false;
    int count = getJdbcTemplate().queryForObject(selectSql, Integer.class, id);
    if(count == 1) flag = true;
    return flag;
  }

  @Override
  public List<T> findAll() {
    String selectSql = "SELECT * FROM " + getTableName();
    List<T> all = getJdbcTemplate().query(selectSql,
        BeanPropertyRowMapper.newInstance(getEntityClass()));
    return all;
  }

  @Override
  public List<T> findAllById(Iterable<Integer> ids) {
    List<T> all = new ArrayList<>();
    for (Integer id : ids) {
      T entity = findById(id).get();
      all.add(entity);
    }
    return all;
  }

  @Override
  public void deleteById(Integer id) {
    if (id == null) {
      throw new IllegalArgumentException("ID cannot be null");
    }
    String deleteSql = "DELETE FROM " + getTableName() + " WHERE " + getIdColumnName() +
        "=?";
    getJdbcTemplate().update(deleteSql, id);
  }

  @Override
  public long count() {
    String countSql = "SELECT count(*) FROM " + getTableName();
    return getJdbcTemplate().queryForObject(countSql, Long.class);
  }

  @Override
  public void deleteAll() {
    String deleteSql = "DELETE FROM " + getTableName();
    getJdbcTemplate().update(deleteSql);
  }
}
