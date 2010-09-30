package com.cloudera.h2.mr.scheduler;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestFIFOScheduler {

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
}
