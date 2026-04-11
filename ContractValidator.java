package org.blockchain.contract;

public class ContractValidator {
    public boolean validateDeployment(SmartContract contract) {
        return contract.getContractAddress() != null && !contract.getContractAddress().isEmpty();
    }

    public boolean validateExecution(String method, Object[] params) {
        if (method == null || method.isBlank()) return false;
        for (Object param : params) {
            if (param == null) return false;
        }
        return true;
    }

    public boolean validateStateChange(String key, Object value) {
        return key != null && !key.isBlank() && value != null;
    }
}
