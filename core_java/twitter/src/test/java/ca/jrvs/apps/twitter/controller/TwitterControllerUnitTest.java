package ca.jrvs.apps.twitter.controller;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {

  @Mock
  Service service;

  @InjectMocks
  TwitterController controller;

  @Test
  public void postTweet() {
    //test failed request
    try {
      String[] args = {"post", "How is your day?", "1:-1", "field"};
      controller.postTweet(args);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    //test happy path
    when(service.postTweet(any())).thenReturn(new Tweet());
    String[] args = {"post", "How is your day?", "1:-1"};
    Tweet tweet = controller.postTweet(args);
    assertNotNull(tweet);
  }

  @Test
  public void showTweet() {
    //test failed request
    when(service.showTweet(isNotNull(), isNotNull())).thenThrow(new IllegalArgumentException("mock"));
    try {
      String[] args = {"show", "1234", "1:-1"};
      controller.showTweet(args);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    //test happy path
    when(service.showTweet(anyString(), any())).thenReturn(new Tweet());
    String[] args = {"show", "1232456", "id,text"};
    Tweet tweet = controller.showTweet(args);
    assertNotNull(tweet);
  }
  @Test
  public void deleteTweet() {
    try {
      String[] args = {"delete", "1234", "12345"};
      controller.deleteTweet(args);
      fail();
    } catch (IllegalArgumentException e) {
      assertTrue(true);
    }

    //test happy path
    when(service.deleteTweets(any())).thenReturn(new ArrayList<Tweet>());
    String[] args = {"show", "1232456, 34567"};
    List<Tweet> tweets= controller.deleteTweet(args);
    assertNotNull(tweets);
  }
}
