package ca.jrvs.apps.twitter.util;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Hashtag;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.List;

public class TweetUtil {

  /**
   * Create a Tweet Object
   * @param text post status
   * @param latitude
   * @param longitude
   * @return a Tweet Object
   */
  public static Tweet buildTweet(String text, Double latitude,Double longitude) {
    Tweet tweet = new Tweet();
    tweet.setText(text);

    //set coordinates
    List<Double> coord = new ArrayList<>();
    coord.add(latitude);
    coord.add(longitude);
    Coordinates coordinates = new Coordinates();
    coordinates.setCoordinates(coord);
    tweet.setCoordinates(coordinates);

    return tweet;
  }
}
