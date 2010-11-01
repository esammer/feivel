package net.lifeless.feivel.mr.scheduler;

import java.util.concurrent.TimeUnit;

public interface Scheduler<T> {

  public boolean schedule(T item);

  public T poll(long timeout, TimeUnit unit) throws InterruptedException;

  public long getCount();

}
