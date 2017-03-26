package com.moelholm.kot

import com.moelholm.One
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class OneTests {

  @Test
  fun test() {

    val one = One("Hello from Kotlin")

    assertThat(one.message).isEqualTo("Hello from Kotlin")

    // This won't compile, as there is no setter:

    // one.message = "Hello from Kotlin";

  }

}