package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterServiceTest {
  private TwitterService service;

  @Before
  public void setUp() throws Exception {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
    CrdDao dao = new TwitterDao(httpHelper);
    this.service = new TwitterService(dao);
  }

  @Test
  public void postTweet() {
    String hashtag = "#test";
    String text = "hello " + hashtag + " " + System.currentTimeMillis();
    Double lat = 2d;
    Double lon = -2d;
    Tweet tweet = TweetUtil.buildTweet(text, lat, lon);
    Tweet newTweet = service.postTweet(tweet);
    assertEquals(text, newTweet.getText());
    assertNotNull(newTweet.getCoordinates());
    assertEquals(lat, newTweet.getCoordinates().getCoordinates().get(1));
    assertEquals(lon, newTweet.getCoordinates().getCoordinates().get(0));
  }

  @Test
  public void showTweet() throws Exception{
    String hashtag = "#test";
    String text = "hello " + hashtag + " " + System.currentTimeMillis();
    Double lat = 2d;
    Double lon = -2d;
    Tweet tweet = TweetUtil.buildTweet(text, lat, lon);
    Tweet newTweet = service.postTweet(tweet);
    Tweet showTweet = service.showTweet(newTweet.getIdStr(), new String[0]);
    assertNotNull(tweet.getCoordinates());
    assertEquals(lat, showTweet.getCoordinates().getCoordinates().get(1));
    assertEquals(lon, showTweet.getCoordinates().getCoordinates().get(0));
    System.out.println(JsonParser.toJson(showTweet, true, false));
  }

  @Test
  public void deleteTweets() throws Exception {
    String[] ids = new String[2];
    String hashtag = "#test";
    Double lat = 2d;
    Double lon = -2d;
    for (int i = 0; i < 2; i++) {
      String text = "hello " + hashtag + " " + System.currentTimeMillis();
      Tweet tweet = TweetUtil.buildTweet(text, lat, lon);
      Tweet newTweet = service.postTweet(tweet);
      ids[i] = newTweet.getIdStr();
    }
    System.out.println(ids.toString());

    List<Tweet> tweets = service.deleteTweets(ids);
    assertNotNull(tweets.get(0).getCoordinates());
    assertEquals(lat, tweets.get(0).getCoordinates().getCoordinates().get(1));
    assertEquals(lon, tweets.get(0).getCoordinates().getCoordinates().get(0));
    System.out.println(JsonParser.toJson(tweets.get(0), true, false));
  }
}