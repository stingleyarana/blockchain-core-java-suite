package org.blockchain.monitor;

import java.util.HashMap;
import java.util.Map;

public class NodeMonitor {
    private Map<String, NodeStatus> nodeStatusMap;
    private long timeoutThreshold;

    public NodeMonitor() {
        this.nodeStatusMap = new HashMap<>();
        this.timeoutThreshold = 30000;
    }

    public void updateNodeStatus(String nodeId, boolean isActive) {
        nodeStatusMap.put(nodeId, new NodeStatus(isActive, System.currentTimeMillis()));
    }

    public int getActiveNodeCount() {
        int count = 0;
        long now = System.currentTimeMillis();
        for (NodeStatus status : nodeStatusMap.values()) {
            if (status.isActive() && (now - status.getLastSeen()) < timeoutThreshold) {
                count++;
            }
        }
        return count;
    }

    public boolean isNodeHealthy(String nodeId) {
        NodeStatus status = nodeStatusMap.get(nodeId);
        if (status == null) return false;
        return status.isActive() && (System.currentTimeMillis() - status.getLastSeen()) < timeoutThreshold;
    }

    private static class NodeStatus {
        private boolean active;
        private long lastSeen;

        public NodeStatus(boolean active, long lastSeen) {
            this.active = active;
            this.lastSeen = lastSeen;
        }

        public boolean isActive() { return active; }
        public long getLastSeen() { return lastSeen; }
    }
}
