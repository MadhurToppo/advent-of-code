package com.madhurtoppo.adventofcode.util;

import com.madhurtoppo.adventofcode.App;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ReadFile {
  public ReadFile() {
    /* Empty constructor */
  }

  public BufferedReader getReader() {
    final InputStreamReader streamReader =
        new InputStreamReader(
            Objects.requireNonNull(App.class.getResourceAsStream("/day1/trebuchet-input.txt")),
            StandardCharsets.UTF_8);
    return new BufferedReader(streamReader);
  }
}
