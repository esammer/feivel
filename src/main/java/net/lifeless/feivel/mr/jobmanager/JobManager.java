package net.lifeless.feivel.mr.jobmanager;

import net.lifeless.feivel.mr.Job;
import net.lifeless.feivel.mr.JobAttempt;

public interface JobManager {

  public JobAttempt submitJob(Job job);

}