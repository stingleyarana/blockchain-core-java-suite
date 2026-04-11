package org.blockchain.discovery;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashSet;
import java.util.Set;

public class PeerDiscovery {
    private static final int DISCOVERY_PORT = 8888;
    private Set<String> discoveredPeers;

    public PeerDiscovery() {
        this.discoveredPeers = new HashSet<>();
    }

    public void startBroadcast() {
        new Thread(() -> {
            try (DatagramSocket socket = new DatagramSocket()) {
                socket.setBroadcast(true);
                String message = "BLOCKCHAIN_NODE_DISCOVERY";
                byte[] buffer = message.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length,
                        InetAddress.getByName("255.255.255.255"), DISCOVERY_PORT);
                socket.send(packet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void listenForPeers() {
        new Thread(() -> {
            try (DatagramSocket socket = new DatagramSocket(DISCOVERY_PORT)) {
                byte[] buffer = new byte[1024];
                while (true) {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);
                    String peer = packet.getAddress().getHostAddress();
                    discoveredPeers.add(peer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public Set<String> getDiscoveredPeers() {
        return discoveredPeers;
    }
}
