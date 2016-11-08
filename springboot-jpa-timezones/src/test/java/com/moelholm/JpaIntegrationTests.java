package com.moelholm;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.TimeZone;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"utc", "mariadb"})
public class JpaIntegrationTests {

  static {
    TimeZone.setDefault(TimeZone.getTimeZone("GMT+2"));
  }

  @Autowired
  private MessageRepository messageRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void testJpa() {

    //
    // Given
    //

    // 1) This is the time we will insert
    LocalDateTime localDateTime = LocalDateTime.of(2017, Month.JANUARY, 1, 0, 0, 0);

    // 2) This is the time we expect the database to receive
    LocalDateTime gmtDateTime = LocalDateTime.of(2016, Month.DECEMBER, 31, 22, 0, 0);

    //
    // When
    //

    messageRepository.save(new Message("Hello World from JPA", localDateTime));

    //
    // Then
    //

    Message fromDatabase = messageRepository.findOne("Hello World from JPA");

    // 1) Datetime is unchanged
    assertThat(fromDatabase.getCreated()).isEqualTo(localDateTime);

    // 2) We expect the database to store the date in the GMT timezone ( == UTC )
    jdbcTemplate.query("select date_format(created, '%Y-%m-%dT%k:%i:%s') from message", rs -> {
      assertThat(LocalDateTime.parse(rs.getString(1))).isEqualTo(gmtDateTime);
    });

  }

}
