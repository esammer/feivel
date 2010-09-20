package com.cloudera.h2.mr;

import java.util.Set;

public class Job {

  private long id;
  private Set<Task> mapTasks;
  private Set<Task> reduceTasks;

  public Job() {
    id = 0;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Set<Task> getMapTasks() {
    return mapTasks;
  }

  public void setMapTasks(Set<Task> mapTasks) {
    this.mapTasks = mapTasks;
  }

  public Set<Task> getReduceTasks() {
    return reduceTasks;
  }

  public void setReduceTasks(Set<Task> reduceTasks) {
    this.reduceTasks = reduceTasks;
  }

}
