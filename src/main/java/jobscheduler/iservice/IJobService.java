package jobscheduler.iservice;

import jobscheduler.pojo.JobPojo;

public interface IJobService {

    public String submitJob(JobPojo jobPojo);
    public String checkJobStatus(String jobId);
    public void updateStatus(String jobId);
}
