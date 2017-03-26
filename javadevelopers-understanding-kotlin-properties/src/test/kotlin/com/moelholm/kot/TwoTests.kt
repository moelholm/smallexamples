package com.moelholm.kot

import com.moelholm.Two
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TwoTests {

  @Test
  fun test() {

    val two = Two("Hello from Kotlin")

    assertThat(two.message).isEqualTo("Hello from Kotlin")

    two.message = "Hello again from Kotlin"

    assertThat(two.message).isEqualTo("Hello again from Kotlin")

  }

}