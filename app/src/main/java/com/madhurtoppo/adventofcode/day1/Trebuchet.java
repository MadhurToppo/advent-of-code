package com.madhurtoppo.adventofcode.day1;

import com.madhurtoppo.adventofcode.util.ReadFile;
import java.io.BufferedReader;
import java.util.Map;

public class Trebuchet {

  private final ReadFile readFile = new ReadFile();

  private final Map<String, String> digitMap =
      Map.of(
          "one", "o1e", "two", "t2o", "three", "t3e", "four", "f4r", "five", "f5e", "six", "s6x",
          "seven", "s7n", "eight", "e8t", "nine", "n9e");

  public int part1() {

    final BufferedReader reader = readFile.getReader();

    return reader
        .lines()
        .map(this::getAllDigits)
        .filter(s -> !s.isEmpty())
        .map(this::getFirstAndLastDigit)
        .mapToInt(Integer::parseInt)
        .sum();
  }

  public int part2() {

    final BufferedReader reader = readFile.getReader();

    return reader
        .lines()
        .map(this::replaceWrittenDigits)
        .map(this::getAllDigits)
        .filter(s -> !s.isEmpty())
        .map(this::getFirstAndLastDigit)
        .mapToInt(Integer::parseInt)
        .sum();
  }

  private String replaceWrittenDigits(String input) {

    for (final Map.Entry<String, String> entry : digitMap.entrySet())
      input = input.replaceAll(entry.getKey(), entry.getValue());
    return input;
  }

  private String getAllDigits(final String input) {
    return input.replaceAll("[a-z]", "");
  }

  private String getFirstAndLastDigit(final String input) {
    return input.charAt(0) + "" + input.charAt(input.length() - 1);
  }
}
