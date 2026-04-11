package org.blockchain.identity;

import org.blockchain.util.StringUtil;
import java.security.KeyPair;

public class NodeIdentity {
    private String nodeId;
    private KeyPair keyPair;
    private String networkAddress;

    public NodeIdentity(KeyPair keyPair, String address) {
        this.keyPair = keyPair;
        this.networkAddress = address;
        this.nodeId = generateNodeId();
    }

    private String generateNodeId() {
        String publicKeyStr = StringUtil.getStringFromKey(keyPair.getPublic());
        return StringUtil.applySha256(publicKeyStr + networkAddress);
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getNetworkAddress() {
        return networkAddress;
    }
}
