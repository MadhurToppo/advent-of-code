package com.madhurtoppo.adventofcode.day2;

import com.madhurtoppo.adventofcode.util.ReadFile;
import java.io.BufferedReader;

public class CubeConundrum {

  private final ReadFile readFile = new ReadFile("/day2/cube-conundrum-input.txt");
  private final BufferedReader reader = readFile.getReader();

  public int part1() {
    int gamesPossible = 0;

    for (String line : reader.lines().toList()) {
      if (line.isBlank()) {
        continue;
      }

      String[] gameParts = line.split(":");
      String[] subsets = gameParts[1].split(";");

      String game = gameParts[0].substring(5);

      boolean gamePossible = true;

      for (String subset : subsets) {
        subset = subset.strip();

        String[] parts = subset.split(",");

        int red = 0, green = 0, blue = 0;

        for (String part : parts) {
          part = part.strip();

          String[] item = part.split(" ");

          int count = Integer.parseInt(item[0]);
          String type = item[1];

          switch (type) {
            case "red" -> red += count;
            case "green" -> green += count;
            case "blue" -> blue += count;
          }
        }

        if (red > 12 || green > 13 || blue > 14) {
          gamePossible = false;
          break;
        }
      }

      if (gamePossible) {
        gamesPossible += Integer.parseInt(game);
      }
    }
    return gamesPossible;
  }

  public int part2() {
    int power = 0;

    for (String line : reader.lines().toList()) {
      if (line.isBlank()) {
        continue;
      }

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
