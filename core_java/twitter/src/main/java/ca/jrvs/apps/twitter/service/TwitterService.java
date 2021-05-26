package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class TwitterService implements Service{

  private CrdDao dao;

  @Autowired
  public TwitterService(CrdDao dao) {
    this.dao = dao;
  }

  /**
   * Validate and post a user input Tweet
   *
   * @param tweet tweet to be created
   * @return created tweet
   * @throws IllegalArgumentException if text exceed max number of allowed characters or lat/long
   *                                  out of range
   */
  @Override
  public Tweet postTweet(Tweet tweet) {

    validateTweet(tweet);

    return (Tweet) dao.create(tweet);
  }

  /**
   * Search a tweet by ID
   *
   * @param id     tweet id
   * @param fields set fields not in the list to null
   * @return Tweet object which is returned by the Twitter API
   * @throws IllegalArgumentException if id or fields param is invalid
   */
  @Override
  public Tweet showTweet(String id, String[] fields) {
    if (!id.matches("[0-9]+") || Long.parseLong(id) > Long.MAX_VALUE) {
      throw new IllegalArgumentException("Invalid Id");
    }

    String[] topLevelFields = new String[]{"create_at", "id", "id_str", "text", "entities", "coordinates",
      "retweet_count", "favorite_count", "favorited", "retweeted"};
    if (fields != null) {
      for (String field : fields) {
        if (Arrays.binarySearch(topLevelFields, field) == -1) {
          throw new IllegalArgumentException("Invalid field(s)");
        }
      }
    }

    return (Tweet) dao.findById(id);
  }

  /**
   * Delete Tweet(s) by id(s).
   *
   * @param ids tweet IDs which will be deleted
   * @return A list of Tweets
   * @throws IllegalArgumentException if one of the IDs is invalid.
   */
  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    List<Tweet> tweets = new ArrayList<>();
    for (String id : ids) {
      if (!id.matches("[0-9]+")) {
        throw new IllegalArgumentException("Invalid Id");
      }
      tweets.add((Tweet) dao.deleteById(id));
    }
    return tweets;
  }

  private void validateTweet(Tweet tweet) {
    // check text length, lan/lon range, id format
    if (tweet.getText().length() > 280) {
      throw new IllegalArgumentException("Text exceeded 280 characters");
    }

    Double lat = tweet.getCoordinates().getCoordinates().get(0);
    Double lon = tweet.getCoordinates().getCoordinates().get(1);
    if (lat < -90d || lat > 90d || lon < -180d || lon > 180d) {
      throw new IllegalArgumentException("Latitude or Longitude out of bound");
    }
  }
}
