package jobscheduler.service;

import jobscheduler.iservice.IJobService;
import jobscheduler.pojo.JobPojo;

public class JobService implements IJobService {
    @Override
    public String submitJob(JobPojo jobPojo) {
        return null;
    }

    @Override
    public String checkJobStatus(String jobId) {
        return null;
    }

    @Override
    public void updateStatus(String jobId) {

    }
}
