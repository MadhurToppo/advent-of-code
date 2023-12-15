package com.madhurtoppo.adventofcode.util;

import com.madhurtoppo.adventofcode.App;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ReadFile {

  private final String file;

  public ReadFile(String file) {
    this.file = file;
  }

  public BufferedReader getReader() {
    final InputStreamReader streamReader =
        new InputStreamReader(
            Objects.requireNonNull(App.class.getResourceAsStream(file)), StandardCharsets.UTF_8);
    return new BufferedReader(streamReader);
  }
}
