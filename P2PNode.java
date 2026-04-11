package org.blockchain.network;

import java.net.*;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class P2PNode {
    private int port;
    private Set<String> peerNodes;
    private ServerSocket serverSocket;

    public P2PNode(int port) {
        this.port = port;
        this.peerNodes = new HashSet<>();
    }

    public void startServer() {
        try {
            serverSocket = new ServerSocket(port);
            new Thread(() -> {
                while (true) {
                    try {
                        Socket clientSocket = serverSocket.accept();
                        handlePeerConnection(clientSocket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connectToPeer(String ip, int peerPort) {
        peerNodes.add(ip + ":" + peerPort);
    }

    private void handlePeerConnection(Socket socket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String message;
        while ((message = in.readLine()) != null) {
            processPeerMessage(message);
        }
    }

    private void processPeerMessage(String message) {
        if (message.startsWith("CHAIN_SYNC")) {
            // 链同步逻辑
        } else if (message.startsWith("TRANSACTION_BROADCAST")) {
            // 交易广播逻辑
        }
    }
}
