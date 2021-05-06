package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepImp implements JavaGrep {

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  private String regex;
  private String rootPath;
  private String outFile;

  @Override
  public void process() throws IOException {
    List<String> matched = new ArrayList<>();
    for (File file : listFiles(rootPath)) {
      for (String line : readLines(file)) {
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
    List<File> files = new ArrayList<>();
    File root = new File(rootDir);

    File[] fList = root.listFiles();
    if (fList != null) {
      for (File f : fList) {
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
  public List<String> readLines(File inputFile) throws IllegalArgumentException {
    List<String> lines = new ArrayList<>();
    try {
      BufferedReader reader = new BufferedReader(new FileReader(inputFile));
      String line = reader.readLine();
      while (line != null) {
        lines.add(line);
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
    return lines;
  }

  @Override
  public boolean containsPattern(String line) {
    return Pattern.matches(regex, line);
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {
    File file = new File(outFile);
    FileOutputStream fileOut = new FileOutputStream(file);

    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOut));
    for (String line : lines) {
      writer.write(line);
      writer.newLine();
    }
    writer.close();
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
    if (args.length != 3) {
      throw new IllegalArgumentException("Usage: JavaGrep regex rootPath outFile");
    }

    BasicConfigurator.configure();

    JavaGrepImp javaGrepImp = new JavaGrepImp();

    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);

    try {
      javaGrepImp.process();
    } catch (Exception ex) {
      javaGrepImp.logger.error(ex.getMessage(), ex);
    }
  }
}
