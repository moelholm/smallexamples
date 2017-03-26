package com.moelholm.kot

import com.moelholm.Second
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SecondTests {

  @Test
  fun test() {

    val second = Second("Hello from Kotlin", "Hello again from Kotlin")

    assertThat(second.messageOne).isEqualTo("Hello from Kotlin")
    assertThat(second.messageTwo).isEqualTo("Hello again from Kotlin")

    second.messageTwo = "You are mutable :)"

    assertThat(second.messageTwo).isEqualTo("You are mutable :)")

  }

}