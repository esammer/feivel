package net.lifeless.feivel.mr.client;

import net.lifeless.feivel.mr.Job;
import net.lifeless.feivel.mr.JobAttempt;
import net.lifeless.feivel.mr.client.SimpleClient;
import net.lifeless.feivel.mr.jobmanager.SimpleJobManager;
import net.lifeless.feivel.mr.scheduler.FIFOScheduler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


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
