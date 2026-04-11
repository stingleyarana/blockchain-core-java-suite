package org.blockchain;

import org.blockchain.api.ApiServer;
import org.blockchain.core.BlockchainCore;
import org.blockchain.config.ConfigLoader;

public class BlockchainApplication {
    public static void main(String[] args) {
        ConfigLoader config = new ConfigLoader("application.properties");
        BlockchainCore blockchain = new BlockchainCore();
        
        blockchain.addBlock("First Block Data");
        blockchain.addBlock("Second Block Data");
        
        System.out.println("Blockchain Valid: " + blockchain.validateChainIntegrity());
        
        try {
            ApiServer apiServer = new ApiServer(config.getInt("api.port"), blockchain);
            apiServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
