package com.cloudera.h2.mr;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestJob {

  private Job job;

  @Before
  public void setup() {
    job = new Job();
  }

  @Test
  public void testJob() {
    Assert.assertNotNull(job);
  }
}
