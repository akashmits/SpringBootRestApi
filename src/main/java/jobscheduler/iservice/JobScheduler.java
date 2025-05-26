package jobscheduler.iservice;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.PriorityBlockingQueue;

public class JobScheduler {
    private ClusterManager clusterManager;
    private PriorityBlockingQueue<Job> jobQueue;

    public JobScheduler() {
        clusterManager = ClusterManager.getInstance();
        jobQueue = new PriorityBlockingQueue<>();
    }

    private void submitJob(Job job) {
        validateJob(job);
        jobQueue.add(job);
    }

    private void validateJob(Job job) {
        if (job.getRequiredCpu() <= 0 ||
                job.getRequiredRam() <= 0 ||
                job.getExecutionTimeInSeconds() <= 0) {
            throw new IllegalArgumentException("Invalid job");
        }
        if (Objects.isNull(
                clusterManager.checkAvailableCluster(job.getRequiredCpu(),
                        job.getRequiredRam()))) {
            throw new IllegalArgumentException("No cluster available for the job");
        }
    }

    public void schedule(List<Job> jobs) {
        jobs.forEach(this::submitJob);
        while (!jobQueue.isEmpty()) {
            Job job = jobQueue.poll();
            System.out.println("Picked job "+job.getId() + " from queue");
            Cluster cluster = clusterManager.getAvailableCluster(job.getRequiredCpu(), job.getRequiredRam());
            if (Objects.isNull(cluster)) {
                System.out.println("No cluster available for the job "+job.getId());
                jobQueue.offer(job);
                continue;
            }
            new Thread(() -> executeJob(cluster, job)).start();
        }
    }

    private void executeJob(Cluster cluster, Job job) {
        long currentTime = System.currentTimeMillis();
        System.out.println("Job " + job.getId() + " started on cluster "+cluster.getId() + " at " + currentTime);
        try {
            Thread.sleep(job.getExecutionTimeInSeconds() * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            cluster.deallocateResource(job.getRequiredCpu(), job.getRequiredRam());
        }
        System.out.println("Job " + job.getId() + " completed on cluster "+cluster.getId() + " at " + System.currentTimeMillis());
        System.out.println("Total time taken for job "+job.getId()+" is "+(System.currentTimeMillis()-currentTime)/1000+"s");
    }
}