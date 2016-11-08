package com.moelholm;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DateFormatSqlFunctionProvider {

  /**
   * Creates a MariaDB or an H2 date formatting SQL function call.
   */
  public static String formatDate(Environment e, String column) {

    List<String> activeProfiles = Arrays.asList(e.getActiveProfiles());

    if (activeProfiles.stream().anyMatch(p -> p.equals("mariadb"))) {
      return "date_format(" + column + ", '%Y-%m-%dT%k:%i:%s')";
    } else {
      return "to_char(" + column + ", 'yyyy-MM-dd\"T\"HH24:MI:ss')";
    }

  }

}
