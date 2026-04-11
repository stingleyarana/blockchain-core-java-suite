package org.blockchain.sync;

import org.blockchain.core.Block;
import org.blockchain.core.BlockchainCore;
import java.util.List;

public class ChainSyncService {
    private BlockchainCore localChain;

    public ChainSyncService(BlockchainCore localChain) {
        this.localChain = localChain;
    }

    public boolean syncWithPeer(List<Block> peerChain) {
        if (peerChain.size() <= localChain.chain.size()) return false;
        if (!validatePeerChain(peerChain)) return false;
        
        localChain.chain.clear();
        localChain.chain.addAll(peerChain);
        return true;
    }

    private boolean validatePeerChain(List<Block> chain) {
        for (int i = 1; i < chain.size(); i++) {
            Block current = chain.get(i);
            Block previous = chain.get(i - 1);
            if (!current.hash.equals(current.calculateHash()) || !current.previousHash.equals(previous.hash)) {
                return false;
            }
        }
        return true;
    }
}
