package org.blockchain.consensus;

import org.blockchain.core.Block;

public class ConsensusPoW {
    private final int miningDifficulty;

    public ConsensusPoW(int difficulty) {
        this.miningDifficulty = difficulty;
    }

    public boolean validateBlockProof(Block block) {
        String targetPrefix = new String(new char[miningDifficulty]).replace('\0', '0');
        return block.calculateHash().startsWith(targetPrefix);
    }

    public String adjustDifficulty(long blockTime, long targetBlockTime) {
        if (blockTime < targetBlockTime / 2) {
            return "Increase Difficulty";
        } else if (blockTime > targetBlockTime * 2) {
            return "Decrease Difficulty";
        }
        return "Maintain Current Difficulty";
    }
}
