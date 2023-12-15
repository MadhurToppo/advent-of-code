package com.madhurtoppo.adventofcode.day2;

import com.madhurtoppo.adventofcode.util.ReadFile;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Map;

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
        .allMatch(m -> isRoundPossible(m.get("red"), m.get("green"), m.get("blue")));
  }

  private boolean isRoundPossible(Integer red, Integer green, Integer blue) {
    return red <= RED_MAX && green <= GREEN_MAX && blue <= BLUE_MAX;
  }

  private String getGameId(String line) {
    return line.split(":")[0].substring(5);
  }

  private Map<String, Integer> getColorsMap(String round) {
    int red = 0, green = 0, blue = 0;
    for (String colorsSet : round.split(",")) {
      String[] colorCount = colorsSet.strip().split(" ");
      int sum;
      sum = Integer.parseInt(colorCount[0]);
      String type = colorCount[1];
      switch (type) {
        case "red" -> red += sum;
        case "green" -> green += sum;
        case "blue" -> blue += sum;
      }
    }
    return Map.of("red", red, "green", green, "blue", blue);
  }

  public int part2() {
    int power = 0;

    for (String line : reader.lines().toList()) {

      String[] gameParts = line.split(":");
      String[] parts = gameParts[1].replaceAll(";", ",").split(",");

      int red = -1, green = -1, blue = -1;

      for (String part : parts) {
        part = part.strip();

        String[] item = part.split(" ");

        int count = Integer.parseInt(item[0]);
        String type = item[1];

        switch (type) {
          case "red" -> {
            if (red == -1 || count > red) {
              red = count;
            }
          }
          case "green" -> {
            if (green == -1 || count > green) {
              green = count;
            }
          }
          case "blue" -> {
            if (blue == -1 || count > blue) {
              blue = count;
            }
          }
        }
      }
      power += (red * green * blue);
    }
    return power;
  }
}
