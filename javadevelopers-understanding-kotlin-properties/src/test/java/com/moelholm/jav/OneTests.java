package com.moelholm.jav;

import static org.assertj.core.api.Assertions.assertThat;

import com.moelholm.One;
import org.junit.Test;

public class OneTests {

  @Test
  public void test() {

    One one = new One("Hello from Java");

    assertThat(one.getMessage()).isEqualTo("Hello from Java");

    // This won't compile, as there is no setter:

    // one.setMessage("Hello from Java");

  }

}
