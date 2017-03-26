package com.moelholm.jav;

import static org.assertj.core.api.Java6Assertions.assertThat;

import com.moelholm.First;
import org.junit.Test;

public class FirstTests {

  @Test
  public void test() {

    First first = new First();

    first.setMessageOne("Hello from Java");

    assertThat(first.getMessageOne()).isEqualTo("Hello from Java");

    assertThat(first.getMessageTwo()).isEqualTo("two");

  }

}
