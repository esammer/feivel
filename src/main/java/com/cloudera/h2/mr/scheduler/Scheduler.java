package com.cloudera.h2.mr.scheduler;

public interface Scheduler<T> {

  public boolean schedule(T item);

  public T poll();

}
