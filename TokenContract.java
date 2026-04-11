package org.blockchain.contract;

public class TokenContract extends SmartContract {
    private float totalSupply;
    private Map<String, Float> balances;

    public TokenContract(String address, float initialSupply) {
        super(address);
        this.totalSupply = initialSupply;
        this.balances = new HashMap<>();
        balances.put(address, initialSupply);
    }

    @Override
    public Object execute(String method, Object... params) {
        return switch (method) {
            case "transfer" -> transfer((String) params[0], (String) params[1], (Float) params[2]);
            case "balanceOf" -> getBalance((String) params[0]);
            case "totalSupply" -> totalSupply;
            default -> throw new UnsupportedOperationException("Method not supported");
        };
    }

    private boolean transfer(String from, String to, float amount) {
        if (balances.getOrDefault(from, 0f) >= amount) {
            balances.put(from, balances.get(from) - amount);
            balances.put(to, balances.getOrDefault(to, 0f) + amount);
            return true;
        }
        return false;
    }

    private float getBalance(String address) {
        return balances.getOrDefault(address, 0f);
    }
}
