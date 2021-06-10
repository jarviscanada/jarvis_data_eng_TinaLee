package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.TweetUtil;
import javax.xml.bind.SchemaOutputResolver;
import org.junit.Before;
import org.junit.Test;

public class TwitterDaoTest {
  private TwitterDao twitterDao;
  @Before
  public void setUp() throws Exception {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
    this.twitterDao = new TwitterDao(httpHelper);
  }

  @Test
  public void create() {
    String hashtag = "#test";
    String text = "four " + hashtag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -14.789d;
    Tweet tweet = TweetUtil.buildTweet(text, lat, lon);
    Tweet createdTweet = twitterDao.create(tweet);
    assertEquals(text, createdTweet.getText());
    assertNotNull(createdTweet.getCoordinates());
    assertEquals(lat, createdTweet.getCoordinates().getCoordinates().get(1));
    assertEquals(lon, createdTweet.getCoordinates().getCoordinates().get(0));
    assertTrue(hashtag.contains(createdTweet.getEntities().getHashtags().get(0).getText()));
  }

  @Test
  public void findById() throws Exception{
    String hashtag = "#test";
    String text = "four " + hashtag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -14.789d;
    Tweet createdTweet = twitterDao.create(TweetUtil.buildTweet(text, lat, lon));
    Tweet showTweet = twitterDao.findById(createdTweet.getIdStr());
    assertNotNull(showTweet.getCoordinates());
    assertEquals(lat, showTweet.getCoordinates().getCoordinates().get(1));
    assertEquals(lon, showTweet.getCoordinates().getCoordinates().get(0));
    System.out.println(JsonParser.toJson(showTweet, true, false));
  }

  @Test
  public void deleteById() throws Exception{
    String hashtag = "#test";
    String text = "four " + hashtag + " " + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -14.789d;
    Tweet createdTweet = twitterDao.create(TweetUtil.buildTweet(text, lat, lon));
    Tweet deleteTweet = twitterDao.deleteById(createdTweet.getIdStr());
    assertNotNull(deleteTweet.getCoordinates());
    assertEquals(lat, deleteTweet.getCoordinates().getCoordinates().get(1));
    assertEquals(lon, deleteTweet.getCoordinates().getCoordinates().get(0));
    System.out.println(JsonParser.toJson(deleteTweet, true, false));
  }

}