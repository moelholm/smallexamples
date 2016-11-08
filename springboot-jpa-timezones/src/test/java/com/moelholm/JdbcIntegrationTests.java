package com.moelholm;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Calendar;
import java.util.TimeZone;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles({"utc", "mariadb"})
public class JdbcIntegrationTests {

  static {
    TimeZone.setDefault(TimeZone.getTimeZone("GMT+2"));
  }

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void testJdbc() {

    //
    // Given
    //

    // 1) This Calendar ensures that Timestamps are stored in the specified timezone:
    //    > Ignored when using useLegacyDatetimeCode=true (which is default for MariaDB) <
    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));

    // 2) This is the time we will insert
    LocalDateTime localDateTime = LocalDateTime.of(2017, Month.JANUARY, 1, 0, 0, 0);

    // 3) This is the time we expect the database to receive
    LocalDateTime gmtDateTime = LocalDateTime.of(2016, Month.DECEMBER, 31, 22, 0, 0);

    //
    // When
    //

    jdbcTemplate.update("insert into message values (?, ?)", ps -> {
      ps.setString(1, "Hello World from JDBC");
      ps.setTimestamp(2, Timestamp.valueOf(localDateTime), cal);
    });

    //
    // Then
    //

    // 1) We expect to get the time back in the JVM's timezone:
    jdbcTemplate.query("select * from message", rs -> {
      assertThat(rs.getTimestamp("created", cal).toLocalDateTime()).isEqualTo(localDateTime);
    });

    // 2) We expect the database to store the date in the GMT timezone ( == UTC )
    jdbcTemplate.query("select date_format(created, '%Y-%m-%dT%k:%i:%s') from message", rs -> {
      assertThat(LocalDateTime.parse(rs.getString(1))).isEqualTo(gmtDateTime);
    });

  }

}
