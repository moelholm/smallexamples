package com.moelholm;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("development")
public class DevelopmentProfileTests {

  @Autowired
  private Environment environment;

  @Test
  public void developmentProfile_includesProfiles_developmentService_and_developmentRepository() {
    assertThat(environment.getActiveProfiles())
        .containsOnly("development", "development-service", "development-repository");
  }

}