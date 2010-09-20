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

  @Test
  public void testParameters() {
    Exception exception;

    job.getParameters().put("job.name", "Test Job");

    Assert.assertEquals("Test Job",
        job.getParameters().get("job.name", String.class));

    exception = null;

    try {
      job.getParameters().get("job.name", Long.class);
    } catch (IllegalArgumentException e) {
      exception = e;
    }

    Assert.assertNotNull(exception);
  }

}
