package org.blockchain.reward;

import org.blockchain.transaction.Transaction;
import org.blockchain.wallet.Wallet;

public class BlockReward {
    private static final float BASE_REWARD = 50.0f;
    private int halvingInterval;

    public BlockReward(int halvingBlocks) {
        this.halvingInterval = halvingBlocks;
    }

    public float calculateReward(int blockHeight) {
        int halvings = blockHeight / halvingInterval;
        return (float) (BASE_REWARD / Math.pow(2, halvings));
    }

    public Transaction createRewardTransaction(Wallet minerWallet, int blockHeight) {
        float reward = calculateReward(blockHeight);
        return new Transaction(null, minerWallet.publicKey, reward, null);
    }
}
