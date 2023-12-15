package com.madhurtoppo.adventofcode.day2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CubeConundrumTest {

  private final CubeConundrum cubeConundrum = new CubeConundrum();

  @Test
  void part1Test() {
    assertEquals(2476, cubeConundrum.part1());
  }

  @Test
  void part2Test() {
    assertEquals(54911, cubeConundrum.part2());
  }
}
