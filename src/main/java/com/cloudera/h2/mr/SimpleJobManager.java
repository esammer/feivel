package com.cloudera.h2.mr;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.cloudera.h2.mr.util.observer.Observable;
import com.cloudera.h2.mr.util.observer.Observer;
import com.cloudera.h2.mr.util.observer.ObserverEvent;
import com.cloudera.h2.mr.util.observer.ObserverSupport;

public class SimpleJobManager implements JobManager, Observable {

  private Map<String, JobAttempt> activeJobs;
  private ObserverSupport observerSupport;

  public SimpleJobManager() {
    activeJobs = new HashMap<String, JobAttempt>();
    observerSupport = new ObserverSupport();
  }

  public synchronized void addJobAttempt(JobAttempt jobAttempt) {
    activeJobs.put(jobAttempt.getId(), jobAttempt);
  }

  public synchronized Collection<JobAttempt> getActiveAttempts() {
    return activeJobs.values();
  }

  @Override
  public synchronized JobAttempt submitJob(Job job) {
    JobAttempt attempt;

    attempt = JobAttempt.withJob(job);

    addJobAttempt(attempt);

    observerSupport.dispatchEvent(ObserverEvent.withTypeAndSubject(
        "jobmanager.SUBMIT_JOB", attempt));

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

}
