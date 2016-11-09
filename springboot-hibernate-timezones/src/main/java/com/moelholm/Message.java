package com.moelholm;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
class Message {

  @Id // Don't do this at home :)
  private String message;

  @Column
  private LocalDateTime created;

  public Message() {
  }

  public Message(String message, LocalDateTime created) {
    this.message = message;
    this.created = created;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }
}
