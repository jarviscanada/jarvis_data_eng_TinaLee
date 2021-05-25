package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserMention {
  private int id;
  private String idStr;
  private List<Integer> indices;
  private String name;
  private String screenName;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getIdStr() {
    return idStr;
  }

  public void setIdStr(String idStr) {
    this.idStr = idStr;
  }

  public List<Integer> getIndices() {
    return indices;
  }

  public void setIndices(List<Integer> indices) {
    this.indices = indices;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getScreenName() {
    return screenName;
  }

  public void setScreenName(String screenName) {
    this.screenName = screenName;
  }

  @Override
  public String toString() {
    return "UserMention{" +
        "id=" + id +
        ", idStr='" + idStr + '\'' +
        ", indices=" + indices +
        ", name='" + name + '\'' +
        ", screenName='" + screenName + '\'' +
        '}';
  }
}
