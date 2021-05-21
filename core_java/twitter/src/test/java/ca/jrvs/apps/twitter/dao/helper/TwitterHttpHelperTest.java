package ca.jrvs.apps.twitter.dao.helper;

import static org.junit.Assert.*;

import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TwitterHttpHelperTest {

  @Test
  public void httpPost() throws Exception {
    TwitterHttpHelper helper = new TwitterHttpHelper(System.getenv("consumerKey"),
        System.getenv("consumerSecret"), System.getenv("accessToken"),
        System.getenv("tokenSecret"));

    String resource = "https://api.twitter.com/1.1/statuses/update.json?status=happy_day";
    URI uri = new URI(resource);
    HttpResponse postResponse = helper.httpPost(uri);
    System.out.println(EntityUtils.toString(postResponse.getEntity()));
  }

  @Test
  public void httpGet() throws Exception{
    TwitterHttpHelper helper = new TwitterHttpHelper(System.getenv("consumerKey"),
        System.getenv("consumerSecret"), System.getenv("accessToken"),
        System.getenv("tokenSecret"));

    String resource = "https://api.twitter.com/1.1/statuses/show.json?id=";
    String id = "1395735569091633152";
    URI uri = new URI(resource + id);
    HttpResponse postResponse = helper.httpGet(uri);
    System.out.println(EntityUtils.toString(postResponse.getEntity()));
  }
}