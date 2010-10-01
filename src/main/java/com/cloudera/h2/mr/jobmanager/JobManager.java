package com.cloudera.h2.mr.jobmanager;

import com.cloudera.h2.mr.Job;
import com.cloudera.h2.mr.JobAttempt;

public interface JobManager {

  public JobAttempt submitJob(Job job);

}