package com.madhurtoppo.adventofcode.day1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TrebuchetTest {
  private final Trebuchet trebuchet = new Trebuchet();

  @Test
  void part1Test() {
    assertEquals(54990, trebuchet.part1());
  }

  @Test
  void part2Test() {
    assertEquals(54473, trebuchet.part2());
  }
}
