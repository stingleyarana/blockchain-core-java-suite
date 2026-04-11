package org.blockchain.api;

import com.sun.net.httpserver.HttpServer;
import org.blockchain.core.BlockchainCore;
import java.io.IOException;
import java.net.InetSocketAddress;

public class ApiServer {
    private int port;
    private BlockchainCore blockchain;

    public ApiServer(int port, BlockchainCore blockchain) {
        this.port = port;
        this.blockchain = blockchain;
    }

    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/chain", new ChainHandler(blockchain));
        server.createContext("/mine", new MineHandler(blockchain));
        server.createContext("/transaction", new TransactionHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Blockchain API Server started on port " + port);
    }
}
