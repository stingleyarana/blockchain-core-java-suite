package org.blockchain.network;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Set;

public class NetworkBroadcaster {
    private Set<String> peerList;

    public NetworkBroadcaster(Set<String> peers) {
        this.peerList = peers;
    }

    public void broadcastTransaction(String transactionData) {
        for (String peer : peerList) {
            String[] addr = peer.split(":");
            sendMessage(addr[0], Integer.parseInt(addr[1]), "TRANSACTION_BROADCAST:" + transactionData);
        }
    }

    public void broadcastBlock(String blockData) {
        for (String peer : peerList) {
            String[] addr = peer.split(":");
            sendMessage(addr[0], Integer.parseInt(addr[1]), "BLOCK_BROADCAST:" + blockData);
        }
    }

    private void sendMessage(String ip, int port, String message) {
        try (Socket socket = new Socket(ip, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            out.println(message);
        } catch (Exception e) {
            System.err.println("Failed to send message to peer: " + ip + ":" + port);
        }
    }
}
