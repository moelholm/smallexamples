package com.moelholm;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Greeting {

  private static int idGenerator;

  @JsonProperty
  private int id;

  @JsonProperty
  private String author;

  @JsonProperty
  private String message;

  Greeting withId() {
    this.id = ++idGenerator;
    return this;
  }

  public int getId() {
    return id;
  }
}
