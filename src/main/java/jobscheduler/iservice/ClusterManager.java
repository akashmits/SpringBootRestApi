package jobscheduler.iservice;

import java.util.ArrayList;
import java.util.List;

public class ClusterManager {
    private static ClusterManager instance;
    private List<Cluster> clusters;

    private ClusterManager() {
        clusters = new ArrayList<>();
    }

    public static synchronized ClusterManager getInstance() {
        if (instance == null) {
            instance = new ClusterManager();
        }
        return instance;
    }

    public void addCluster(Cluster cluster) {
        clusters.add(cluster);
    }

    public Cluster getAvailableCluster(int cpu, int ram) {
        for (Cluster cluster: clusters) {
            if (cluster.allocateResource(cpu, ram)) {
                return cluster;
            }
            System.out.println("Cluster "+cluster.getId()+" is full");
        }
        return null;
    }

    public Cluster checkAvailableCluster(int cpu, int ram) {
        for (Cluster cluster: clusters) {
            if (cluster.isResourceAvailable(cpu, ram)) {
                return cluster;
            }
        }
        return null;
    }
}