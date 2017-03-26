package com.moelholm.jav;

import static org.assertj.core.api.Java6Assertions.assertThat;

import com.moelholm.Three;
import org.junit.Test;

public class ThreeTests {

  @Test
  public void test() {

    Three three = new Three();

    three.setMessageOne("Hello from Java");

    assertThat(three.getMessageOne()).isEqualTo("Hello from Java");

    assertThat(three.getMessageTwo()).isNull();

  }

}
