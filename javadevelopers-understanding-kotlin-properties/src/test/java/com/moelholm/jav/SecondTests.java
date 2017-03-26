package com.moelholm.jav;

import static org.assertj.core.api.Assertions.assertThat;

import com.moelholm.Second;
import org.junit.Test;

public class SecondTests {

  @Test
  public void test() {

    Second second = new Second("Hello from Java", "Hello again from Java");

    assertThat(second.getMessageOne()).isEqualTo("Hello from Java");
    assertThat(second.getMessageTwo()).isEqualTo("Hello again from Java");

    second.setMessageTwo("You are mutable :)");

    assertThat(second.getMessageTwo()).isEqualTo("You are mutable :)");

  }

}
