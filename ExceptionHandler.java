package org.blockchain.exception;

public class ExceptionHandler {
    public static void handleChainException(ChainException e) {
        System.err.println("Chain Error: " + e.getMessage());
    }

    public static void handleCryptoException(CryptoException e) {
        System.err.println("Crypto Error: " + e.getMessage());
    }

    public static void handleNetworkException(NetworkException e) {
        System.err.println("Network Error: " + e.getMessage());
    }

    public static void handleGeneralException(Exception e) {
        System.err.println("System Error: " + e.getMessage());
    }
}
