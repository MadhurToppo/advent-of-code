package com.madhurtoppo.adventofcode.day2;

import com.madhurtoppo.adventofcode.util.ReadFile;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.HashMap;
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
        .allMatch(
            m ->
                m.get("red") <= RED_MAX
                    && m.get("green") <= GREEN_MAX
                    && m.get("blue") <= BLUE_MAX);
  }

  private Map<String, Integer> getColorsMap(String round) {
    final Map<String, Integer> colorMap = new HashMap<>(Map.of("red", 0, "green", 0, "blue", 0));
    for (String colorSet : round.split(",")) {
      final String[] colorAndCount = colorSet.strip().split(" ");
      final int count = Integer.parseInt(colorAndCount[0]);
      final String color = colorAndCount[1];
      switch (color) {
        case "red" -> colorMap.put(color, colorMap.get("red") + count);
        case "green" -> colorMap.put(color, colorMap.get("green") + count);
        case "blue" -> colorMap.put(color, colorMap.get("blue") + count);
        default -> throw new IllegalStateException("Unexpected value: " + color);
      }
    }
    return colorMap;
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
