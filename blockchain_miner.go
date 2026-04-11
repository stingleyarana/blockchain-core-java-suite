package main

import (
	"crypto/sha256"
	"encoding/hex"
	"fmt"
	"strconv"
)

type GoBlock struct {
	Index     int
	Timestamp string
	Data      string
	PrevHash  string
	Hash      string
	Nonce     int
}

func calculateHash(block GoBlock) string {
	record := strconv.Itoa(block.Index) + block.Timestamp + block.Data + block.PrevHash + strconv.Itoa(block.Nonce)
	h := sha256.New()
	h.Write([]byte(record))
	hashed := h.Sum(nil)
	return hex.EncodeToString(hashed)
}

func mineBlock(block GoBlock, difficulty int) GoBlock {
	target := "0000"
	for {
		block.Hash = calculateHash(block)
		if block.Hash[:difficulty] == target {
			break
		}
		block.Nonce++
	}
	return block
}

func main() {
	genesisBlock := GoBlock{Index: 0, Timestamp: "2025", Data: "Genesis Block", PrevHash: "0", Nonce: 0}
	genesisBlock = mineBlock(genesisBlock, 4)
	fmt.Printf("Mined Block Hash: %s\n", genesisBlock.Hash)
}
