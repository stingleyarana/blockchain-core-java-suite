package org.blockchain.manager;

import org.blockchain.core.Block;
import org.blockchain.core.BlockchainCore;
import java.util.List;

public class ChainManager {
    private BlockchainCore blockchain;

    public ChainManager(BlockchainCore blockchain) {
        this.blockchain = blockchain;
    }

    public List<Block> getFullChain() {
        return blockchain.chain;
    }

    public int getChainHeight() {
        return blockchain.chain.size();
    }

    public Block getLatestBlock() {
        if (blockchain.chain.isEmpty()) return null;
        return blockchain.chain.get(blockchain.chain.size() - 1);
    }

    public boolean syncChain(List<Block> externalChain) {
        if (externalChain.size() > getChainHeight() && validateExternalChain(externalChain)) {
            blockchain.chain = externalChain;
            return true;
        }
        return false;
    }

    private boolean validateExternalChain(List<Block> chain) {
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
