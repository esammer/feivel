package com.cloudera.h2.mr;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestSimpleClient {

  private SimpleClient client;

  @Before
  public void setup() {
    client = new SimpleClient();

    client.setJobManager(new SimpleJobManager());
  }

  @Test
  public void testClient() {
    Assert.assertNotNull(client);
  }

  @Test
  public void testSubmitJob() {
    Job job;
    JobAttempt attempt;

    job = new Job();

    attempt = client.submitJob(job);

    Assert.assertNotNull(attempt);
    Assert.assertEquals(job, attempt.getJob());
  }

}
