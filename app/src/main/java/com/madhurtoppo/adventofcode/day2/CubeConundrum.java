package com.madhurtoppo.adventofcode.day2;

import com.madhurtoppo.adventofcode.util.ReadFile;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class CubeConundrum {

  private static final int RED_MAX = 12;
  private static final int GREEN_MAX = 13;
  private static final int BLUE_MAX = 14;
  private final ReadFile readFile = new ReadFile("/day2/cube-conundrum-input.txt");
  private final BufferedReader reader = readFile.getReader();

  public int part1() {
    return reader
        .lines()
        .filter(this::isGamePossible)
        .map(this::getGameId)
        .mapToInt(Integer::parseInt)
        .sum();
  }

  private boolean isGamePossible(String line) {
    return Arrays.stream(line.split(":")[1].split(";"))
        .map(this::getColorsMap)
        .allMatch(this::isValidColorMap);
  }

  private boolean isValidColorMap(Map<String, Integer> colorMap) {
    return colorMap.getOrDefault("red", 0) <= RED_MAX
        && colorMap.getOrDefault("green", 0) <= GREEN_MAX
        && colorMap.getOrDefault("blue", 0) <= BLUE_MAX;
  }

  private Map<String, Integer> getColorsMap(String round) {
    return Arrays.stream(round.split(","))
        .map(String::strip)
        .map(colorSet -> colorSet.split(" "))
        .collect(
            Collectors.groupingBy(
                colorAndCount -> colorAndCount[1],
                Collectors.summingInt(colorAndCount -> Integer.parseInt(colorAndCount[0]))));
  }

  private String getGameId(String line) {
    return line.split(":")[0].substring(5);
  }

  public int part2() {
    int power = 0;
    for (String line : reader.lines().toList()) {
      String[] gameParts = line.split(":");
      String[] parts = gameParts[1].replace(";", ",").split(",");
      int red = -1, green = -1, blue = -1;
      for (String part : parts) {
        final String[] colorAndCount = part.strip().split(" ");
        final int count = Integer.parseInt(colorAndCount[0]);
        final String color = colorAndCount[1];
        switch (color) {
          case "red" -> red = (red == -1 || count > red) ? count : red;
          case "green" -> green = (green == -1 || count > green) ? count : green;
          case "blue" -> blue = (blue == -1 || count > blue) ? count : blue;
          default -> throw new IllegalStateException("Unexpected value: " + color);
        }
      }
      power += (red * green * blue);
    }
    return power;
  }
}
