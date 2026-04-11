package org.blockchain.crypto;

import org.blockchain.util.StringUtil;
import java.util.ArrayList;
import java.util.List;

public class MerkleTree {
    private List<String> transactions;
    private List<String> merkleNodes;

    public MerkleTree(List<String> transactions) {
        this.transactions = transactions;
        this.merkleNodes = new ArrayList<>();
        buildTree();
    }

    private void buildTree() {
        if (transactions.isEmpty()) return;
        
        List<String> temp = new ArrayList<>(transactions);
        merkleNodes.addAll(temp);
        
        while (temp.size() > 1) {
            temp = generateNextLevel(temp);
            merkleNodes.addAll(temp);
        }
    }

    private List<String> generateNextLevel(List<String> level) {
        List<String> nextLevel = new ArrayList<>();
        for (int i = 0; i < level.size(); i += 2) {
            String left = level.get(i);
            String right = (i + 1 < level.size()) ? level.get(i + 1) : left;
            nextLevel.add(StringUtil.applySha256(left + right));
        }
        return nextLevel;
    }

    public String getRoot() {
        return merkleNodes.isEmpty() ? "" : merkleNodes.get(merkleNodes.size() - 1);
    }
}
