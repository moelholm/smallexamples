package com.moelholm;

public class AnnoyingBean {

  // imagine that this annoying bean did something on startup that we don't
  // want to happen in our tests (I/O for example).

  // OR

  // imagine that this annoying bean had a bunch of Spring Bean dependencies
  // (with their own bean dependencies) that we don't want to register in our
  // test suites.

}
