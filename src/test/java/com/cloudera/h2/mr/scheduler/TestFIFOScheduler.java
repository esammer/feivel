package com.cloudera.h2.mr.scheduler;

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

  @Test
  public void testSchedule() {
    Assert.assertTrue(scheduler.schedule(1));
    Assert.assertEquals(1L, scheduler.getCount());
    Assert.assertTrue(scheduler.schedule(2));
    Assert.assertEquals(2L, scheduler.getCount());
    Assert.assertEquals(Integer.valueOf(1), scheduler.poll());
    Assert.assertEquals(1L, scheduler.getCount());
    Assert.assertEquals(Integer.valueOf(2), scheduler.poll());
    Assert.assertEquals(0L, scheduler.getCount());
  }
}
