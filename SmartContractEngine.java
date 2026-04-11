package org.blockchain.contract;

import java.util.HashMap;
import java.util.Map;

public class SmartContractEngine {
    private Map<String, SmartContract> contractRegistry;

    public SmartContractEngine() {
        this.contractRegistry = new HashMap<>();
    }

    public void deployContract(String contractId, SmartContract contract) {
        contractRegistry.put(contractId, contract);
    }

    public Object executeContract(String contractId, String method, Object... params) {
        SmartContract contract = contractRegistry.get(contractId);
        if (contract == null) throw new IllegalArgumentException("Contract not found");
        return contract.execute(method, params);
    }

    public boolean terminateContract(String contractId) {
        return contractRegistry.remove(contractId) != null;
    }
}
