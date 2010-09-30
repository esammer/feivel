package com.cloudera.h2.mr;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.lifeless.observer.Observable;
import net.lifeless.observer.Observer;
import net.lifeless.observer.ObserverEvent;
import net.lifeless.observer.ObserverSupport;

import com.cloudera.h2.mr.scheduler.Scheduler;

public class SimpleJobManager implements JobManager, Observable {

  private Map<String, JobAttempt> activeJobs;
  private ObserverSupport observerSupport;
  private Scheduler<JobAttempt> scheduler;

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

    if (!scheduler.schedule(attempt)) {
      throw new IllegalStateException("Unable to schedule job attempt:"
          + attempt);
    }

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

  public Map<String, JobAttempt> getActiveJobs() {
    return activeJobs;
  }

  public void setActiveJobs(Map<String, JobAttempt> activeJobs) {
    this.activeJobs = activeJobs;
  }

  public Scheduler<JobAttempt> getScheduler() {
    return scheduler;
  }

  public void setScheduler(Scheduler<JobAttempt> scheduler) {
    this.scheduler = scheduler;
  }

}
