package com.moelholm.jav;

import static org.assertj.core.api.Java6Assertions.assertThat;

import com.moelholm.Two;
import org.junit.Test;

public class TwoTests {

  @Test
  public void test() {

    Two two = new Two("Hello from Java");

    assertThat(two.getMessage()).isEqualTo("Hello from Java");

    two.setMessage("Hello again from Java");

    assertThat(two.getMessage()).isEqualTo("Hello again from Java");

  }

}
