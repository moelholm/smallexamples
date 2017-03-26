package com.moelholm.kot

import com.moelholm.Three
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ThreeTests {

  @Test
  fun test() {

    val three = Three()

    three.messageOne = "Hello from Java"

    assertThat(three.messageOne).isEqualTo("Hello from Java")

    assertThat(three.messageTwo).isNull()

  }

}