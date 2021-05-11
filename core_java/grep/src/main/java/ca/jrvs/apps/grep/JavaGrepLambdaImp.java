package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.BasicConfigurator;

public class JavaGrepLambdaImp extends JavaGrepImp {

  public JavaGrepLambdaImp() {
    super();
  }

  @Override
  public List<File> listFiles(String rootDir) {
    Path path = Paths.get(rootDir);
    try {
      List<File> files = Files.walk(path)
          .filter(Files::isRegularFile)
          .map(Path::toFile)
          .collect(Collectors.toList());
      return files;
    } catch (IOException e) {
      logger.error("Invalid input", e);
    }
    return null;
  }

  @Override
  public List<String> readLines(File inputFile) throws IllegalArgumentException {
    Path path = Paths.get(inputFile.getAbsolutePath());
    try {
      return Files.lines(path).collect(Collectors.toList());
    } catch (IOException e) {
      logger.error("Cannot read lines", e);
    }
    return null;
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
      javaGrepImp.logger.error("Invalid input", ex);
    }
  }
}
