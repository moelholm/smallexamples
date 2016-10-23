package com.moelholm;

public interface BusinessEventLogger {

  void log(String messageTemplate, Object ... args);

}
