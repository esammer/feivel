package net.lifeless.feivel.mr;

public class JobAttempt {

  private String id;
  private Job job;

  public static JobAttempt withJob(Job job) {
    JobAttempt attempt;

    attempt = new JobAttempt();

    attempt.setId(JobAttempt.generateId(job));
    attempt.setJob(job);

    return attempt;
  }

  public static String generateId(Job job) {
    return generateId(job, 0);
  }

  public static String generateId(Job job, int attempt) {
    String attemptId;

    attemptId = "ja-" + job.getId() + "-" + attempt;

    return attemptId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Job getJob() {
    return job;
  }

  public void setJob(Job job) {
    this.job = job;
  }

}
