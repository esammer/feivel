package com.cloudera.h2.mr;

public interface Client {

  public JobAttempt submitJob(Job job);

}
