package ca.jrvs.apps.twitter.service;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.when;
import static junit.framework.TestCase.fail;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {

  @Mock
  CrdDao dao;

  @InjectMocks
  TwitterService service;

  @Test
  public void postTweet() throws Exception{
    String text = "today is a great day" + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;
    // test failed request
    when(dao.create(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      service.postTweet(TweetUtil.buildTweet(text, lat, lon));
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }

    //test happy path
    when(dao.create(any())).thenReturn(new Tweet());
    Tweet tweet = service.postTweet(TweetUtil.buildTweet("hello", 1d, -1d));
    assertNotNull(tweet);
  }

  @Test
  public void showTweet() {
    String text = "today is a great day" + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;
    String[] fields = {"id", "text", "id_str"};
    // test failed request
    try {
      service.showTweet(TweetUtil.buildTweet(text, lat, lon).getIdStr(), fields);
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }

    //test happy path
    when(dao.findById(any())).thenReturn(new Tweet());
    Tweet tweet = service.showTweet("34895012749304283",fields);
    assertNotNull(tweet);
  }

  @Test
  public void deleteTweets() {
    String text = "today is a great day" + System.currentTimeMillis();
    Double lat = 1d;
    Double lon = -1d;
    String[] fields = {"id", "text", "id_str"};
    // test failed request
    when(dao.deleteById(isNotNull())).thenThrow(new RuntimeException("mock"));
    try {
      service.deleteTweets(new String[]{"12345", "12456"});
      fail();
    } catch (RuntimeException e) {
      assertTrue(true);
    }

    //test happy path
    when(dao.deleteById(any())).thenReturn(new Tweet());
    List<Tweet> tweets = service.deleteTweets(new String[]{"12345", "34566"});
    assertNotNull(tweets);
  }
}
