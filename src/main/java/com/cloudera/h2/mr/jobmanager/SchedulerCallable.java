package com.cloudera.h2.mr.jobmanager;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloudera.h2.mr.scheduler.Scheduler;

public class SchedulerCallable<T extends Scheduler<T>> implements
    Callable<Boolean> {

  private static final Logger logger = LoggerFactory
      .getLogger(SchedulerCallable.class);

  private T attempt;
  private Scheduler<T> scheduler;

  @Override
  public Boolean call() throws Exception {
    logger.info("Scheduling attempt:" + attempt);

    return scheduler.schedule(attempt);
  }

  public T getAttempt() {
    return attempt;
  }

  public void setAttempt(T attempt) {
    this.attempt = attempt;
  }

  public Scheduler<T> getScheduler() {
    return scheduler;
  }

  public void setScheduler(Scheduler<T> scheduler) {
    this.scheduler = scheduler;
  }

}
