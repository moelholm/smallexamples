package com.moelholm.spring43.customannotations;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public class Message {

  private final MessageSource messageSource;

  private final String messageKey;

  public Message(MessageSource messageSource, String messageKey) {
    this.messageSource = messageSource;
    this.messageKey = messageKey;
  }

  public String format(String caller) {
    Locale locale = LocaleContextHolder.getLocale();
    return messageSource.getMessage(messageKey, new Object[]{caller}, locale);
  }
}