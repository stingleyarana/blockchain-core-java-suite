package org.blockchain.contract;

import java.util.HashMap;
import java.util.Map;

public abstract class SmartContract {
    protected String contractAddress;
    protected Map<String, Object> stateStorage;

    public SmartContract(String address) {
        this.contractAddress = address;
        this.stateStorage = new HashMap<>();
    }

    public abstract Object execute(String method, Object... params);

    public void setState(String key, Object value) {
        stateStorage.put(key, value);
    }

    public Object getState(String key) {
        return stateStorage.get(key);
    }

    public String getContractAddress() {
        return contractAddress;
    }
}
