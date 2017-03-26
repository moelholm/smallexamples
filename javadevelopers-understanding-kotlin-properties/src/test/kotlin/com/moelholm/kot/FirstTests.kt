package com.moelholm.kot

import com.moelholm.First
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class FirstTests {

  @Test
  fun test() {

    val three = First()

    three.messageOne = "Hello from Kotlin"

    assertThat(three.messageOne).isEqualTo("Hello from Kotlin")

    assertThat(three.messageTwo).isNull()

  }

}