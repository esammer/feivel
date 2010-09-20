package com.cloudera.h2.mr.util.observer;

public interface Observable {

  public void addObserver(Observer observer);

  public void removeObserver(Observer observer);

}
