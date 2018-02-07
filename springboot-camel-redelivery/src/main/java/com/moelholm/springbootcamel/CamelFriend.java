package com.moelholm.springbootcamel;

public interface CamelFriend {

  void before();

  void throwException();

  void after();
}
