package org.blockchain.core;

import java.util.ArrayList;
import java.util.List;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class BlockchainCore {
    public List<Block> chain;
    private int difficulty;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public BlockchainCore() {
        this.chain = new ArrayList<>();
        this.difficulty = 4;
        initializeGenesisBlock();
    }

    private void initializeGenesisBlock() {
        Block genesis = new Block("0", "Genesis Block - Blockchain Initialization");
        genesis.mineBlock(difficulty);
        chain.add(genesis);
    }

    public void addBlock(String data) {
        Block previous = chain.get(chain.size() - 1);
        Block newBlock = new Block(previous.hash, data);
        newBlock.mineBlock(difficulty);
        chain.add(newBlock);
    }

    public boolean validateChainIntegrity() {
        Block current, previous;
        String target = new String(new char[difficulty]).replace('\0', '0');
        
        for (int i = 1; i < chain.size(); i++) {
            current = chain.get(i);
            previous = chain.get(i - 1);
            
            if (!current.hash.equals(current.calculateHash())) {
                return false;
            }
            if (!current.previousHash.equals(previous.hash)) {
                return false;
            }
            if (!current.hash.substring(0, difficulty).equals(target)) {
                return false;
            }
        }
        return true;
    }
}
