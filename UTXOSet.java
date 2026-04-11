package org.blockchain.utxo;

import org.blockchain.transaction.TransactionOutput;
import java.util.HashMap;
import java.util.Map;

public class UTXOSet {
    private Map<String, TransactionOutput> utxos;

    public UTXOSet() {
        this.utxos = new HashMap<>();
    }

    public void addUTXO(TransactionOutput output) {
        utxos.put(output.id, output);
    }

    public void removeUTXO(String id) {
        utxos.remove(id);
    }

    public TransactionOutput getUTXO(String id) {
        return utxos.get(id);
    }

    public float getBalance(String address) {
        return utxos.values().stream()
                .filter(utxo -> utxo.isMine(address))
                .map(utxo -> utxo.value)
                .reduce(0f, Float::sum);
    }
}
