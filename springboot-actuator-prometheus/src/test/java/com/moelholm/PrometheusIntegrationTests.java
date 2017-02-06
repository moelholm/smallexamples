package com.moelholm;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrometheusIntegrationTests {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void prometheusAutoConfiguration_whenActive_thenRegistersActuatorEndpoint() {

    // Given
    // ( build.gradle has com.moelholm:prometheus-spring-boot-starter:1.0.0 )

    // When
    ResponseEntity<String> entity = restTemplate.getForEntity("/prometheus", String.class);

    // Then
    assertThat(entity.getStatusCodeValue()).isEqualTo(200);
    assertThat(entity.getBody()).contains("# HELP heap_used heap_used");
    assertThat(entity.getBody()).contains("# TYPE heap_used gauge");

  }

}