package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Iterables;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MarketDataDao implements CrudRepository<IexQuote, String> {

  private static final String IEX_BATCH_PATH = "/stock/market/batch?symbols=%s&types=quote&token=";
  private final String IEX_BATCH_URL;

  private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
  private HttpClientConnectionManager httpClientConnectionManager;

  //repsonse code
  private static final int HTTP_OK = 200;

  @Autowired
  public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager,
      MarketDataConfig marketDataConfig) {
    this.httpClientConnectionManager = httpClientConnectionManager;
    IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATH + marketDataConfig.getToken();
  }

  @Override
  public <S extends IexQuote> S save(S s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }

  /**
   * Get an IexQuote (helper method which class findAllById)
   *
   * @param ticker
   * @throws IllegalArgumentException if a given ticker is invalid
   * @throws org.springframework.dao.DataRetrievalFailureException if HTTP request failed
   */
  @Override
  public Optional<IexQuote> findById(String ticker) {
    Optional<IexQuote> iexQuote;
    List<IexQuote> quotes = findAllById(Collections.singletonList(ticker));

    if (quotes.size() == 0) {
      return Optional.empty();
    } else if (quotes.size() == 1) {
      iexQuote = Optional.of(quotes.get(0));
    } else {
      throw new DataRetrievalFailureException("Unexpected number of quotes");
    }
    return iexQuote;
  }

  @Override
  public boolean existsById(String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public Iterable<IexQuote> findAll() {
    throw new UnsupportedOperationException("Not implemented");
  }

  /**
   * Get quotes from IEX
   * @param tickers
   * @return a list of IexQuote object
   * @throws IllegalArgumentException if any ticker is invalid or tickers is empty
   * @throws DataRetrievalFailureException if HTTP request failed
   */
  @Override
  public List<IexQuote> findAllById(Iterable<String> tickers) {
    if (Iterables.size(tickers) == 0) {
      throw new IllegalArgumentException("Input ticker is empty");
    }

    String url = String.format(IEX_BATCH_URL, String.join(",", tickers));

    String response = executeHttpGet(url)
        .orElseThrow(() -> new IllegalArgumentException("Invalid ticker"));

    JSONObject IexQuotesJson = new JSONObject(response);

    if (IexQuotesJson.length() == 0) {
      throw new IllegalArgumentException("Invalid ticker");
    }

    List<IexQuote> quotes = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();
    for (String ticker : tickers) {
      if (IexQuotesJson.has(ticker)) {
        try {
          System.out.println(IexQuotesJson.toString());
          IexQuote quote = objectMapper.readValue(IexQuotesJson.getJSONObject(ticker).get("quote").toString(), IexQuote.class);
          quotes.add(quote);
        } catch (IOException e) {
          logger.error("Failed to map JSON to class", e);
        }
      } else {
        throw new IllegalArgumentException("Invalid ticker");
      }
    }

    return quotes;
  }

  /**
   * Execute a get and return http entity/body as a string
   *
   * Tip: use EntityUtils.toString to process HTTP entity
   *
   * @param url resource URL
   * @return http response body or Optional.empty for 404 response
   * @throws DataRetrievalFailureException if HTTP failed or status code is unexpected
   */
  private Optional<String> executeHttpGet(String url) {
    CloseableHttpClient closeableHttpClient = getHttpClient();
    HttpGet httpGet = new HttpGet(url);
    HttpResponse response;
    int status;
    try {
      response = closeableHttpClient.execute(httpGet);
      status = response.getStatusLine().getStatusCode();
    } catch (IOException e) {
      logger.error("HTTP request failed", e);
      throw new DataRetrievalFailureException("HTTP request failed", e);
    }

    String body;
    if (status != HTTP_OK) {
      return Optional.empty();
    }

    if (response.getEntity() == null) {
      throw new RuntimeException("Response body is empty");
    }

    try {
      body = EntityUtils.toString(response.getEntity());
    } catch (IOException e) {
      logger.error("Failed to convert response to String", e);
      throw new RuntimeException("Failed to convert response to String", e);
    }

    return Optional.of(body);
  }

  /**
   * Borrow a HTTP client from the httpClientConnectionManager
   * @return a httpClient
   */
  private CloseableHttpClient getHttpClient() {
    return HttpClients.custom()
        .setConnectionManager(httpClientConnectionManager)
        //prevent connectionManager shutdown when calling httpClient.close()
        .setConnectionManagerShared(true)
        .build();
  }

  @Override
  public long count() {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteById(String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void delete(IexQuote iexQuote) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll(Iterable<? extends IexQuote> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll() {
    throw new UnsupportedOperationException("Not implemented");
  }
}
