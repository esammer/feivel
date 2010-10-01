package com.cloudera.h2.mr.client;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cloudera.h2.mr.Job;
import com.cloudera.h2.mr.JobAttempt;
import com.cloudera.h2.mr.client.SimpleClient;
import com.cloudera.h2.mr.jobmanager.SimpleJobManager;
import com.cloudera.h2.mr.scheduler.FIFOScheduler;

public class TestSimpleClient {

  private SimpleClient client;

  @Before
  public void setup() {
    SimpleJobManager jobManager;

    client = new SimpleClient();
    jobManager = new SimpleJobManager();

    jobManager.setScheduler(new FIFOScheduler<JobAttempt>());
    client.setJobManager(jobManager);
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
