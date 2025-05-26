package jobscheduler.pojo;


import lombok.Data;

@Data
public class JobPojo {
    private long systemId;
    private int ram;
    private int cpu;
    private int priority;
    private int executionTime;
}
