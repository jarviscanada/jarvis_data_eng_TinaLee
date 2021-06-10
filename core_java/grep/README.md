# Introduction

The Java Grep App is a text-search program built using Maven. Given a root directory,
the program will search through all files and subdirectories for a targeted pattern and 
output matched lines to a specified output file. Java I/O, such as FileReader, BufferedReader, 
FileOutputStream, OutputStreamWriter, and BufferedWriter, are utilized to read and write files and 
Java Stream API is used to process List instances. Later, the program is dockerized and uploaded to 
Docker Hub. This project serves as a great introduction to Java concepts and Java fundamentals.

# Quick Start
The app takes three arguments:
* `regex`: a special text string for describing a search pattern
* `rootPath`: root directory path
* `outFile`: output file name

1. Run program using jar file
```
# build package using maven
mvn clean package 

# run jar file
java -jar -cp target/grep-1.0-SNAPSHOT.jar {regex} {rootPath} {outFile}
```

2. Run program using Docker
```
# pull image from docker
docker pull tinalee262008/grep

# build a container
docker #docker run --rm -v `pwd`/data:/data -v `pwd`/out:/out tinalee262008/grep \
${regex} ${rootPath} /out/${outfile}
```

# Implementation
## Pseudocode
`process` method pseudocode
```
matchedLines = []
for file in listFilesRecursively(rootDir)
  for line in readLines(file)
      if containsPattern(line)
        matchedLines.add(line)
writeToFile(matchedLines)
```

## Performance Issue
The Java Grep App cannot be used for processing large files, because this will throw a OutOfMemoryException.
This is caused by List instances returned by `readLines` and `listFiles` methods taking up too much
space in the heap memory. To fix this problem, the returned type of these two methods must be
converted to `Stream`. Stream does not take up any space in the heap memory and would resolve the
memory issues.

# Test
Testings are done using a sample text file and results were validated by comparing the output file 
and expected results.

# Deployment
To dockerize the Java Grep App, a dockerfile must be implemented.
Then `docker build` command is used to build a new docker image locally from a maven-packaged uber file,
and the new image is uploaded to Docker Hub using the `docker push` command.

# Improvement
* Specify source file of each matched lines in the output file
* Replace List with Stream for some methods to prevent memory issues
* Allow users to invert the functionality of finding matched lines