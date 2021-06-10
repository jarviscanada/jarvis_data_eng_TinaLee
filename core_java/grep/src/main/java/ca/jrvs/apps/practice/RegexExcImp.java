package ca.jrvs.apps.practice;

import java.util.regex.Pattern;

public class RegexExcImp implements RegexExc{

  @Override
  public boolean matchJpeg(String filename) {
    return Pattern.matches(".*\\.jpe?g", filename);
  }

  @Override
  public boolean matchIp(String ip) {
    return Pattern.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}", ip);
  }

  @Override
  public boolean isEmptyLine(String line) {
    return Pattern.matches("^\\s*$", line);
  }

  public static void main(String[] args) {
    RegexExcImp test = new RegexExcImp();
    String ip = "1.2.3.4";
    test.matchIp(ip);
    test.matchJpeg("tina.jpeg");
  }
}
