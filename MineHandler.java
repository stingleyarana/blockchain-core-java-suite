package org.blockchain.api;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.blockchain.core.BlockchainCore;
import java.io.IOException;
import java.io.OutputStream;

public class MineHandler implements HttpHandler {
    private BlockchainCore blockchain;

    public MineHandler(BlockchainCore blockchain) {
        this.blockchain = blockchain;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        blockchain.addBlock("New Block Mined via API");
        String response = "{\"status\":\"success\",\"message\":\"Block mined successfully\"}";
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(200, response.length());
        try (OutputStream os = exchangeResponseBody()) {
            os.write(response.getBytes());
        }
    }
}
