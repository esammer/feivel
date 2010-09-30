package com.cloudera.h2.mr.scheduler;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestFIFOScheduler {

  private static final Logger logger = LoggerFactory
      .getLogger(TestFIFOScheduler.class);

  private Scheduler<Integer> scheduler;

  @Before
  public void setup() {
    scheduler = new FIFOScheduler<Integer>();
  }

  @Test
  public void testFIFOScheduler() {
    Assert.assertNotNull(scheduler);
    Assert.assertEquals(0L, scheduler.getCount());
  }

  /*
   * TODO: We should catch InterruptedExceptions here and fail the test.
   * -esammer
   */
  @Test
  public void testSchedule() throws InterruptedException {
    Assert.assertTrue(scheduler.schedule(1));
    Assert.assertEquals(1L, scheduler.getCount());
    Assert.assertTrue(scheduler.schedule(2));
    Assert.assertEquals(2L, scheduler.getCount());
    Assert
        .assertEquals(Integer.valueOf(1), scheduler.poll(1, TimeUnit.SECONDS));
    Assert.assertEquals(1L, scheduler.getCount());
    Assert
        .assertEquals(Integer.valueOf(2), scheduler.poll(1, TimeUnit.SECONDS));
    Assert.assertEquals(0L, scheduler.getCount());
  }

  @Test
  public void testPoll() throws InterruptedException {
    final int expectedCount;
    final AtomicInteger counter;
    Thread worker;

    expectedCount = 10;
    counter = new AtomicInteger();
    worker = new Thread() {
      @Override
      public void run() {
        try {
          Integer item;

          for (int i = 0; i < expectedCount; i++) {
            logger.info("looking for item:" + i);

            item = scheduler.poll(1, TimeUnit.SECONDS);

            if (item != null) {
              counter.incrementAndGet();
            }
          }
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    };

    for (int i = 0; i < expectedCount; i++) {
      scheduler.schedule(i);
    }

    logger.debug("Put " + expectedCount
        + " items into the scheduler. Starting worker.");

    worker.start();

    logger.debug("waiting for worker to finish.");

    worker.join();

    Assert.assertEquals(expectedCount, counter.get());
  }

}
