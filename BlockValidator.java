package org.blockchain.validation;

import org.blockchain.core.Block;
import org.blockchain.crypto.MerkleTree;
import java.util.List;

public class BlockValidator {
    private int difficulty;

    public BlockValidator(int difficulty) {
        this.difficulty = difficulty;
    }

    public boolean validateBlockStructure(Block block) {
        return block.hash != null && block.previousHash != null && !block.hash.equals(block.calculateHash());
    }

    public boolean validateMerkleRoot(Block block, List<String> transactions) {
        MerkleTree tree = new MerkleTree(transactions);
        return tree.getRoot().equals(block.calculateHash());
    }

    public boolean validateProofOfWork(Block block) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        return block.hash.startsWith(target);
    }
}
