package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.MarketDataDao;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import ca.jrvs.apps.trading.model.domain.Quote;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class QuoteService {

  private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);

  private MarketDataDao marketDataDao;
  private QuoteDao quoteDao;

  @Autowired
  public QuoteService(QuoteDao quoteDao, MarketDataDao marketDataDao) {
    this.quoteDao = quoteDao;
    this.marketDataDao = marketDataDao;
  }

  /**
   * Find an IexQuote
   *
   * @param ticker id
   * @return IexQuote object
   * @throws IllegalArgumentException if ticker is invalid
   */
  public IexQuote findIexQuoteByTicker(String ticker) {
    return  marketDataDao.findById(ticker)
        .orElseThrow(() -> new IllegalArgumentException(ticker + " is invalid"));
  }

  /**
   * Update quote table against IEX source
   * - get all quotes from the db
   * - foreach ticker get iexQuote
   * - convert iexQuote to quote entity
   * - persist quote to db
   *
   * @return saved quotes
   */
  public List<Quote> updateMarketData() {
    List<Quote> quotes = quoteDao.findAll();
    List<String> tickers = quotes.stream().map(quote -> quote.getId())
        .collect(Collectors.toList());
    List<IexQuote> iexQuotes = marketDataDao.findAllById(tickers);
    List<Quote> updatedQuotes = iexQuotes.stream().map(iexQuote -> buildQuoteFromIexQuote(iexQuote))
        .collect(Collectors.toList());
    return quoteDao.saveAll(updatedQuotes);
  }

  /**
   * helper method. Map a IexQuote to a Quote entity.
   * Note: `iexQuote.getLastestPrice() == null` if the stock market is closed.
   * Make sure set a default value for number field(s).
   */
  protected static Quote buildQuoteFromIexQuote(IexQuote iexQuote) {
    Quote quote = new Quote();
    quote.setTicker(iexQuote.getSymbol());
    quote.setLastPrice(iexQuote.getLatestPrice());
    quote.setBidPrice(iexQuote.getIexBidPrice());
    quote.setBidSize(iexQuote.getIexBidSize());
    quote.setAskPrice(iexQuote.getIexAskPrice());
    quote.setAskSize(iexQuote.getIexAskSize());
    return quote;
  }

  /**
   * Validate (against IEX) and save given tickers to quote table.
   *
   * - get iexQuote(s)
   * - convert each iex to Quote entity
   * - persist the quote to db
   * @param tickers
   * @return
   */
  public List<Quote> saveQuotes(List<String> tickers) {
    List<IexQuote> iexQuotes = tickers.stream().map(ticker -> findIexQuoteByTicker(ticker))
        .collect(Collectors.toList());
    List<Quote> quotes = iexQuotes.stream().map(iexQuote -> buildQuoteFromIexQuote(iexQuote))
        .collect(Collectors.toList());
    quotes.stream().forEach(quote -> saveQuote(quote));
    return quotes;
  }

  /**
   * helper method
   */
  public Quote saveQuote(String ticker) {
    IexQuote iexQuote = findIexQuoteByTicker(ticker);
    Quote quote = buildQuoteFromIexQuote(iexQuote);
    return saveQuote(quote);
  }

  /**
   * Update a given quote to quote table without validation
   * @param quote entity
   */
  public Quote saveQuote(Quote quote) {
    return quoteDao.save(quote);
  }

  /**
   * Find all quotes from the quote table
   * @return a list of quotes
   */
  public List<Quote> findAllQuotes() {
    return quoteDao.findAll();
  }
}