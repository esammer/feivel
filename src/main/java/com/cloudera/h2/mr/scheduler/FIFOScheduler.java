package com.cloudera.h2.mr.scheduler;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FIFOScheduler<T> implements Scheduler<T> {

  private static final Logger logger = LoggerFactory
      .getLogger(FIFOScheduler.class);

  private Queue<T> queue;

  public FIFOScheduler() {
    queue = new LinkedBlockingQueue<T>();
  }

  @Override
  public synchronized boolean schedule(T item) {
    logger.info("Scheduling item " + item);

    return queue.offer(item);
  }

  @Override
  public synchronized T poll() {
    T item;

    item = queue.poll();

    logger.info("Returning item " + item);

    return item;
  }

  @Override
  public synchronized long getCount() {
    return queue.size();
  }

}
