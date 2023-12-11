package com.madhurtoppo.adventofcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

public class App {
  public String getGreeting() throws IOException {

    final InputStreamReader streamReader = new InputStreamReader(App.class.getResourceAsStream("/input.txt"),
        StandardCharsets.UTF_8);
    final BufferedReader reader = new BufferedReader(streamReader);
    Stream<String> lines = reader.lines();
    lines.forEach(System.out::println);
    return "Hello World!";
  }

  public static void main(final String[] args) throws IOException {
    System.out.println(new App().getGreeting());
  }
}
