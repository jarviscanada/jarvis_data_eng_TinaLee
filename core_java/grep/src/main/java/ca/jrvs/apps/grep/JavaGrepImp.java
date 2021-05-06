package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepImp implements JavaGrep{
  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  private String regex;
  private String rootPath;
  private String outFile;

  @Override
  public void process() throws IOException {
    List<String> matched = new ArrayList<>();
    for (File file: listFiles(rootPath)) {
      for (String line: readLines(file)) {
        if (containsPattern(line)) {
          matched.add(line);
        }
      }
    }
    try {
      writeToFile(matched);
    } catch (IOException e) {
      throw new IOException("Failed to write to file", e);
    }
  }

  @Override
  public List<File> listFiles(String rootDir) {
    List<File> files = new ArrayList<File>();
    File root = new File(rootDir);

    File[] fList = root.listFiles();
    if (fList != null) {
      for (File f: fList) {
        if (f.isFile()) {
          files.add(f);
        } else {
          String filePath = f.getAbsolutePath();
          files.addAll(listFiles(filePath));
        }
      }
    }
    return files;
  }

  @Override
  public List<String> readLines(File inputFile) {
    return null;
  }

  @Override
  public boolean containsPattern(String line) {
    return false;
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {

  }

  @Override
  public String getRegex() {
    return regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getRootPath() {
    return rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  public String getOutFile() {
    return outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }

  public static void main(String[] args) {
/*    if (args.length != 3) {
      throw new IllegalArgumentException("Usage: JavaGrep regex rootPath outFile");
    }*/

    BasicConfigurator.configure();

    JavaGrepImp javaGrepImp = new JavaGrepImp();
/*    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);*/

    List<File> tmp = javaGrepImp.listFiles("/home/leetina4/jarvis_data_eng_TinaLee/core_java/grep");
    for (File file : tmp) {
      System.out.println(file);
    }
/*    try {
      javaGrepImp.process();
    } catch (Exception ex) {
      javaGrepImp.logger.error(ex.getMessage(), ex);
    }*/
  }
}
