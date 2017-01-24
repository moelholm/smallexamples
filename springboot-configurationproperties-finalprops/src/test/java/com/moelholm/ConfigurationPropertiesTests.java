package com.moelholm;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConfigurationPropertiesTests {

  @Autowired
  private FinalProperties finalProperties;

  @Autowired
  private NormalProperties normalProperties;

  @Test
  public void testFinalProperties() {

    assertThat(finalProperties.getMessages())
        .containsExactlyInAnyOrder("a", "b", "c");

  }

  @Test
  public void testNormalProperties() {

    assertThat(normalProperties.getMessages())
        .containsExactlyInAnyOrder("a", "b", "c");

  }

}