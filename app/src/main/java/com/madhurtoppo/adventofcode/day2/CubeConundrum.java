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
        .allMatch(
            colorMap ->
                colorMap.getOrDefault("red", 0) <= RED_MAX
                    && colorMap.getOrDefault("green", 0) <= GREEN_MAX
                    && colorMap.getOrDefault("blue", 0) <= BLUE_MAX);
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
    return reader.lines().toList().stream()
        .map(line -> line.split(":")[1].replace(";", ",").split(","))
        .map(this::calculatePowerForParts)
        .reduce(0, Integer::sum);
  }

  private int calculatePowerForParts(String[] parts) {
    int[] colorCounts = new int[3];
    Arrays.fill(colorCounts, Integer.MIN_VALUE);
    Arrays.stream(parts)
        .map(part -> part.strip().split(" "))
        .forEach(
            colorSet -> {
              final int count = Integer.parseInt(colorSet[0]);
              final String color = colorSet[1];
              final int i =
                  switch (color) {
                    case "red" -> 0;
                    case "green" -> 1;
                    case "blue" -> 2;
                    default -> throw new IllegalStateException("Unexpected value: " + color);
                  };
              colorCounts[i] = Math.max(colorCounts[i], count);
            });
    return Arrays.stream(colorCounts).reduce(Math::multiplyExact).orElse(0);
  }
}
