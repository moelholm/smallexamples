package com.moelholm.kot

import com.moelholm.First
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class FirstTests {

  @Test
  fun test() {

    val first = First()

    first.messageOne = "Hello from Kotlin"

    assertThat(first.messageOne).isEqualTo("Hello from Kotlin")

    assertThat(first.messageTwo).isEqualTo("two")

  }

}