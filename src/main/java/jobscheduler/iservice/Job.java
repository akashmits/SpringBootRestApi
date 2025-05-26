package jobscheduler.iservice;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Job implements Comparable<Job>{
    private String id;
    private int requiredCpu;
    private int requiredRam;
    private int executionTimeInSeconds;
    private int priority; // higher value means higher priority

    @Override
    public int compareTo(Job job) {
        return Integer.compare(job.priority, this.priority);
    }
}