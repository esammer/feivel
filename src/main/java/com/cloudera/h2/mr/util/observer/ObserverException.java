package com.cloudera.h2.mr.util.observer;

public class ObserverException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ObserverException() {
    super();
  }

  public ObserverException(String message) {
    super(message);
  }

  public ObserverException(Throwable t) {
    super(t);
  }

  public ObserverException(String message, Throwable t) {
    super(message, t);
  }

}
