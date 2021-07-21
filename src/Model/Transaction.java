package Model;

import java.time.LocalDateTime;

public abstract class Transaction {
    private String transactionID;
    private String fromAccountID;
    private String toAccoundID;
    private LocalDateTime createdAt;
    private double amount;

    public Transaction(String transactionID, String fromAccountID, String toAccoundID, LocalDateTime createdAt, double amount) {
        this.transactionID = transactionID;
        this.fromAccountID = fromAccountID;
        this.toAccoundID = toAccoundID;
        this.createdAt = createdAt;
        this.amount = amount;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public String getFromAccountID() {
        return fromAccountID;
    }

    public String getToAccoundID() {
        return toAccoundID;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
