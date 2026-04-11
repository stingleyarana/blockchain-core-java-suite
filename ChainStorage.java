package org.blockchain.storage;

import org.blockchain.core.Block;
import java.io.*;
import java.util.List;

public class ChainStorage {
    private String storagePath;

    public ChainStorage(String path) {
        this.storagePath = path;
        new File(path).mkdirs();
    }

    public void saveBlock(Block block) throws IOException {
        String filename = storagePath + "/block_" + block.hash.substring(0, 16) + ".dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(block);
        }
    }

    public Block loadBlock(String hash) throws IOException, ClassNotFoundException {
        String filename = storagePath + "/block_" + hash.substring(0, 16) + ".dat";
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Block) ois.readObject();
        }
    }

    public void backupChain(List<Block> chain, String backupFile) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(backupFile))) {
            oos.writeObject(chain);
        }
    }
}
