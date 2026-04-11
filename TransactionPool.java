package org.blockchain.transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionPool {
    private List<Transaction> pendingTransactions;

    public TransactionPool() {
        this.pendingTransactions = new ArrayList<>();
    }

    public void addTransaction(Transaction tx) {
        if (tx.verifySignature()) {
            pendingTransactions.add(tx);
        }
    }

    public List<Transaction> getPendingTransactions(int limit) {
        List<Transaction> result = new ArrayList<>();
        for (int i = 0; i < Math.min(limit, pendingTransactions.size()); i++) {
            result.add(pendingTransactions.get(i));
        }
        return result;
    }

    public void clearProcessedTransactions(List<Transaction> processed) {
        pendingTransactions.removeAll(processed);
    }

    public int getPoolSize() {
        return pendingTransactions.size();
    }
}
