package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterControllerIntTest {
  private TwitterController controller;

  @Before
  public void setUp() throws Exception {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken,
        tokenSecret);
    CrdDao dao = new TwitterDao(httpHelper);
    Service service = new TwitterService(dao);
    this.controller = new TwitterController(service);
  }

  @Test
  public void postTweet() {
    String hashtag = "#test";
    String text = "hello " + hashtag + " " + System.currentTimeMillis();
    String coor = "23:-23";
    String[] args = {"post", text, coor};
    Tweet tweet = controller.postTweet(args);
    assertEquals(text, tweet.getText());
    assertNotNull(tweet.getCoordinates());
  }

  @Test
  public void showTweet() {
    String hashtag = "#test";
    String text = "hello " + hashtag + " " + System.currentTimeMillis();
    String coor = "23:-23";
    Tweet tweet = controller.postTweet(new String[]{"post", text, coor});
    Tweet showTweet = controller.showTweet(new String[]{"show", tweet.getIdStr(), "id,text"});
    assertEquals(text, showTweet.getText());
    assertNotNull(showTweet.getCoordinates());
  }

  @Test
  public void deleteTweet() {
    String hashtag = "#test";
    String text = "hello " + hashtag + " " + System.currentTimeMillis();
    String coor = "23:-23";
    Tweet tweet = controller.postTweet(new String[]{"post", text, coor});
    List<Tweet> deleteTweet = controller.deleteTweet(new String[]{"delete", tweet.getIdStr()});
    assertEquals(text, deleteTweet.get(0).getText());
    assertNotNull(deleteTweet.get(0).getCoordinates());
  }
}