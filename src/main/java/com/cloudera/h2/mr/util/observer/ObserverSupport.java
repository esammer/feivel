package com.cloudera.h2.mr.util.observer;

import java.util.LinkedList;
import java.util.List;

public class ObserverSupport {

  private List<Observer> observers;

  public ObserverSupport() {
    observers = new LinkedList<Observer>();
  }

  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  public void removeObserver(Observer observer) {
    observers.remove(observer);
  }

  public void dispatchEvent(ObserverEvent<?> event) throws ObserverException {
    for (Observer observer : observers) {
      observer.onEvent(event);
    }
  }

  public List<Observer> getObservers() {
    return observers;
  }

  public void setObservers(List<Observer> observers) {
    this.observers = observers;
  }

}
