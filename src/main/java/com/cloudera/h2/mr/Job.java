package com.cloudera.h2.mr;

import java.util.Set;

import com.cloudera.h2.mr.util.DynamicMap;

public class Job {

  private long id;
  private Set<Task> mapTasks;
  private Set<Task> reduceTasks;
  private DynamicMap parameters;

  public Job() {
    id = 0;
    parameters = new DynamicMap();
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

  public DynamicMap getParameters() {
    return parameters;
  }

  public void setParameters(DynamicMap parameters) {
    this.parameters = parameters;
  }

}
