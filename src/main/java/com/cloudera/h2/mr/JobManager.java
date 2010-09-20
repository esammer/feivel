package com.cloudera.h2.mr;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class JobManager {

  private Map<String, JobAttempt> activeJobs;

  public JobManager() {
    activeJobs = new HashMap<String, JobAttempt>();
  }

  public void addJobAttempt(JobAttempt jobAttempt) {
    activeJobs.put(jobAttempt.getId(), jobAttempt);
  }

  public Collection<JobAttempt> getActiveAttempts() {
    return activeJobs.values();
  }

  public JobAttempt submitJob(Job job) {
    JobAttempt attempt;

    attempt = JobAttempt.withJob(job);

    addJobAttempt(attempt);

    return attempt;
  }

}
