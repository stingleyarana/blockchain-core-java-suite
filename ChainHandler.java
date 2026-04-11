package org.blockchain.api;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.blockchain.core.BlockchainCore;
import java.io.IOException;
import java.io.OutputStream;

public class ChainHandler implements HttpHandler {
    private BlockchainCore blockchain;
    private Gson gson;

    public ChainHandler(BlockchainCore blockchain) {
        this.blockchain = blockchain;
        this.gson = new Gson();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = gson.toJson(blockchain.chain);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, response.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}
