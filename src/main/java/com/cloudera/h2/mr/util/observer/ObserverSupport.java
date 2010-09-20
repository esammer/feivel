package com.cloudera.h2.mr.util.observer;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObserverSupport {

  private static final Logger logger = LoggerFactory
      .getLogger(ObserverSupport.class);

  private List<Observer> observers;

  public ObserverSupport() {
    observers = new LinkedList<Observer>();
  }

  public void addObserver(Observer observer) {
    observers.add(observer);

    logger.info("Added observer:" + observer);
  }

  public void removeObserver(Observer observer) {
    boolean removed;

    removed = observers.remove(observer);

    if (removed) {
      logger.info("Removed observer:" + observer);
    } else {
      logger.warn("Attempt to remove an unknown observer:" + observer);
    }
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
