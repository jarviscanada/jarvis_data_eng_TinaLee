package ca.jrvs.apps.practice;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamExcImp implements LambdaStreamExc{


  @Override
  public Stream<String> createStrStream(String... strings) {
    return Arrays.stream(strings);
  }

  @Override
  public Stream<String> toUpperCase(String... strings) {
    return createStrStream(strings).map(val -> val.toUpperCase());
  }

  @Override
  public Stream<String> filter(Stream<String> stringStream, String pattern) {
    return stringStream.filter(val -> val.contains(pattern));
  }

  @Override
  public IntStream createIntStream(int[] arr) {
    return Arrays.stream(arr);
  }

  @Override
  public <E> List<E> toList(Stream<E> stream) {
    return stream.collect(Collectors.toList());
  }

  @Override
  public List<Integer> toList(IntStream intStream) {
    return intStream.boxed().collect(Collectors.toList());
  }

  @Override
  public IntStream createIntStream(int start, int end) {
    return IntStream.range(start, end + 1);
  }

  @Override
  public DoubleStream squareRootIntStream(IntStream intStream) {
    return intStream.mapToDouble(val -> Math.sqrt(val));
  }

  @Override
  public IntStream getOdd(IntStream intStream) {
    return intStream.filter(val -> val % 2 == 1);
  }

  @Override
  public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
    return txt -> System.out.println(prefix + txt + suffix);
  }

  @Override
  public void printMessages(String[] messages, Consumer<String> printer) {
    for (String msg : messages) {
      printer.accept(msg);
    }
  }

  @Override
  public void printOdd(IntStream intStream, Consumer<String> printer) {
    getOdd(intStream).forEach(val -> printer.accept(String.valueOf(val)));
  }

  @Override
  public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
    return ints.flatMap(val -> val.stream()).map(num -> num * num);
  }

  public static void main(String[] args) {
    LambdaStreamExc lse = new LambdaStreamExcImp();


    String[] tmp = {"Tina", "Lee"};
    lse.createStrStream(tmp).forEach(System.out::println);
    System.out.println("\n");

    lse.toUpperCase(tmp).forEach(System.out::println);
    System.out.println("\n");

    lse.filter(lse.createStrStream(tmp), "a").forEach(System.out::println);
    System.out.println("\n");

    int[] lst = {1 , 2, 3, 4, 5};
    lse.createIntStream(lst).forEach(System.out::println);
    System.out.println("\n");

    Stream<String> stream = lse.createStrStream(tmp);
    lse.toList(stream).forEach(System.out::println);
    System.out.println("\n");

    IntStream intStream = lse.createIntStream(lst);
    lse.toList(intStream).forEach(System.out::println);
    System.out.println("\n");

    lse.createIntStream(3, 8).forEach(System.out::println);
    System.out.println("\n");

    int[] sqr = {4, 16, 9};
    IntStream intStream1 = lse.createIntStream(sqr);
    lse.squareRootIntStream(intStream1).forEach(System.out::println);
    System.out.println("\n");

    IntStream intStream2 = lse.createIntStream(lst);
    lse.getOdd(intStream2).forEach(System.out::println);
    System.out.println("\n");

    Consumer<String> printer = lse.getLambdaPrinter("start>", "<end");
    printer.accept("Message body");
    System.out.println("\n");

    lse.printOdd(lse.createIntStream(0, 5), lse.getLambdaPrinter("odd number:", "!"));
    System.out.println("\n");

    List<List<Integer>> list = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4, 5));
    Stream<List<Integer>> strList = list.stream();
    lse.flatNestedInt(strList).forEach(System.out::println);
  }
}
