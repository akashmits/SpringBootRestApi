package jobscheduler.iservice;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Data
public class Cluster {
    private String id;
    private int totalCpu;
    private int totalRam;
    private int availableCpu;
    private int availableRam;
    private Lock resourceLock;

    public Cluster(String id, int cpu, int ram) {
        this.id = id;
        this.totalCpu = cpu;
        this.totalRam = ram;
        this.availableCpu = this.totalCpu;
        this.availableRam = this.totalRam;
        resourceLock = new ReentrantLock();
    }

    public boolean isResourceAvailable(int jobCpu, int jobRam) {
        return availableCpu >= jobCpu && availableRam >= jobRam;
    }

    public boolean allocateResource(int jobCpu, int jobRam) {
        resourceLock.lock();
        try {
            if (isResourceAvailable(jobCpu, jobRam)) {
                availableCpu -= jobCpu;
                availableRam -= jobRam;
                return true;
            }
            return false;
        } finally {
            resourceLock.unlock();
        }
    }

    public void deallocateResource(int jobCpu, int jobRam) {
        resourceLock.lock();
        try {
            availableCpu += jobCpu;
            availableRam += jobRam;
        } finally {
            resourceLock.unlock();
        }
    }
}