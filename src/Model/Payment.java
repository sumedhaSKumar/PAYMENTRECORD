package Model;

import java.time.LocalDateTime;

public class Payment extends Transaction {

    private boolean hasReversal;

    public Payment(String transactionID, String fromAccountID, String toAccoundID, LocalDateTime createdAt, double amount) {
        super(transactionID, fromAccountID, toAccoundID, createdAt, amount);
        hasReversal = false;
    }

    public boolean isHasReversal() {
        return hasReversal;
    }

    public void setHasReversal(boolean hasReversal) {
        this.hasReversal = hasReversal;
    }
}
