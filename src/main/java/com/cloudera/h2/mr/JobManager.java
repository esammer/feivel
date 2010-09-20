package com.cloudera.h2.mr;

public interface JobManager {

  public JobAttempt submitJob(Job job);

}