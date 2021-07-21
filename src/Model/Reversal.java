package Model;

import Model.Transaction;

import java.time.LocalDateTime;

public class Reversal extends Transaction {

    private String reversalTransactionID;

    public Reversal(String transactionID, String fromAccountID, String toAccoundID, LocalDateTime createdAt, double amount, String reversalTransactionID) {
        super(transactionID, fromAccountID, toAccoundID, createdAt, amount);
        this.reversalTransactionID = reversalTransactionID;
    }
}
