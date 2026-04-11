package org.blockchain.consensus;

import java.util.HashMap;
import java.util.Map;

public class ConsensusPoS {
    private Map<String, Integer> stakeholderBalances;
    private final int minStake;

    public ConsensusPoS() {
        this.stakeholderBalances = new HashMap<>();
        this.minStake = 100;
    }

    public void registerStakeholder(String address, int stake) {
        if (stake >= minStake) {
            stakeholderBalances.put(address, stake);
        }
    }

    public String selectForger() {
        int totalStake = stakeholderBalances.values().stream().mapToInt(Integer::intValue).sum();
        int randomSelector = (int) (Math.random() * totalStake);
        int cumulative = 0;

        for (Map.Entry<String, Integer> entry : stakeholderBalances.entrySet()) {
            cumulative += entry.getValue();
            if (cumulative >= randomSelector) {
                return entry.getKey();
            }
        }
        return null;
    }

    public boolean validateForger(String address) {
        return stakeholderBalances.containsKey(address);
    }
}
