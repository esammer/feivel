package com.cloudera.h2.mr;

import com.cloudera.h2.mr.util.observer.Observable;
import com.cloudera.h2.mr.util.observer.Observer;
import com.cloudera.h2.mr.util.observer.ObserverEvent;
import com.cloudera.h2.mr.util.observer.ObserverSupport;

public class SimpleClient implements Client, Observable {

  private ObserverSupport observerSupport;
  private JobManager jobManager;

  public SimpleClient() {
    observerSupport = new ObserverSupport();
  }

  @Override
  public JobAttempt submitJob(Job job) {
    JobAttempt attempt;

    attempt = jobManager.submitJob(job);

    observerSupport.dispatchEvent(ObserverEvent.withTypeAndSubject(
        "client.SUBMIT_JOB", attempt));

    return attempt;
  }

  @Override
  public void addObserver(Observer observer) {
    observerSupport.addObserver(observer);
  }

  @Override
  public void removeObserver(Observer observer) {
    observerSupport.removeObserver(observer);
  }

  public ObserverSupport getObserverSupport() {
    return observerSupport;
  }

  public void setObserverSupport(ObserverSupport observerSupport) {
    this.observerSupport = observerSupport;
  }

  public JobManager getJobManager() {
    return jobManager;
  }

  public void setJobManager(JobManager jobManager) {
    this.jobManager = jobManager;
  }

}