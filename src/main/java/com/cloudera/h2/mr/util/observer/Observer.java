package com.cloudera.h2.mr.util.observer;


public interface Observer {

  public void onEvent(ObserverEvent<?> event) throws ObserverException;

}
