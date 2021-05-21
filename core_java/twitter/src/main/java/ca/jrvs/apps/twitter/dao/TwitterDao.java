package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Tweet;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class TwitterDao implements CrdDao<Tweet, String> {

  //URI constants
  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show.json";
  private static final String DELETE_PATH = "/1.1/statuses/destroy/";
  //URI symbols
  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  //response code
  private static final int HTTP_OK = 200;

  private HttpHelper httpHelper;

  public TwitterDao(HttpHelper httpHelper) {
    this.httpHelper = httpHelper;
  }

  public URI getPostURI(Tweet tweet) throws URISyntaxException {
    PercentEscaper percentEscaper = new PercentEscaper("", false);
    return new URI(API_BASE_URI + POST_PATH + QUERY_SYM + "status" + EQUAL +
        percentEscaper.escape(tweet.getText()) +
        AMPERSAND + "lat" + EQUAL + tweet.getCoordinates().getCoordinates().get(0) +
            AMPERSAND + "long" + EQUAL + tweet.getCoordinates().getCoordinates().get(1));
  }

  public URI getShowURI(String s) throws URISyntaxException {
    PercentEscaper percentEscaper = new PercentEscaper("", false);
    return new URI(API_BASE_URI + SHOW_PATH + QUERY_SYM + "id" + EQUAL + s);
  }

  public URI getDeleteURI(String s) throws URISyntaxException {
    return new URI(API_BASE_URI + DELETE_PATH + s + ".json");
  }

  /**
   * Create an entity(Tweet) to the underlying storage
   *
   * @param entity entity that to be created
   * @return created entity
   */
  @Override
  public Tweet create(Tweet entity) {
    URI uri;
    try {
      uri = getPostURI(entity);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Invalid input", e);
    }

    HttpResponse response = httpHelper.httpPost(uri);

    return parseResponseBody(response, HTTP_OK);
  }

  /**
   * Find an entity(Tweet) by its id
   *
   * @param s entity id
   * @return Tweet entity
   */
  @Override
  public Tweet findById(String s) {
    URI uri;
    try {
      uri = getShowURI(s);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Invalid input");
    }

    HttpResponse response = httpHelper.httpGet(uri);
    return parseResponseBody(response, HTTP_OK);
  }

  /**
   * Delete an entity(Tweet) by its ID
   *
   * @param s of the entity to be deleted
   * @return deleted entity
   */
  @Override
  public Tweet deleteById(String s) {
    URI uri;
    try {
      uri = getDeleteURI(s);
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException("Invalid input", e);
    }

    HttpResponse response = httpHelper.httpPost(uri);
    return parseResponseBody(response, HTTP_OK);
  }

  /**
   * Check response status and Convert Response Entity to Tweet
   * @param response
   * @param expectedStatusCode
   * @return
   */
  public Tweet parseResponseBody(HttpResponse response, Integer expectedStatusCode) {
    Tweet tweet = null;

    int status = response.getStatusLine().getStatusCode();
    if (status != expectedStatusCode) {
      try {
        EntityUtils.toString(response.getEntity());
      } catch (IOException e) {
        System.out.println("Response entity is empty");
      }
      throw new RuntimeException("Unexpected status: " + status);
    }

    if (response.getEntity() == null) {
      throw new RuntimeException("Response body is empty");
    }

    String jsonStr;
    try {
      jsonStr = EntityUtils.toString(response.getEntity());
    } catch (IOException e) {
      throw new RuntimeException("Failed to convert response to String", e);
    }

    try {
      tweet = JsonParser.toObjectFromJson(jsonStr, Tweet.class);
    } catch (IOException e ) {
      throw new RuntimeException("Failed to convert json string to Object", e);
    }

    return tweet;
  }
}
