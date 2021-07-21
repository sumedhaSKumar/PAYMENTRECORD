import Model.Payment;
import Model.Reversal;
import Model.Transaction;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    ArrayList<Transaction> transactions;

    public Controller() {
        this.transactions = new ArrayList<>();
    }

    //Check for existing accounts
    public boolean accountExists(String accountID) {
        //loop through each transaction
        for (int i = 0; i<this.transactions.size(); i++){
            Transaction t = transactions.get(i);
            //check if the input account id is same as to or from account id in each transaction
            if ((t.getToAccoundID().compareTo(accountID)) == 0 || (t.getFromAccountID().compareTo(accountID)==0))
                return true;
        }
        return false;
    }

    public void processCSVData(String filePath, DateTimeFormatter format)throws Exception {
        ArrayList<Transaction> transactions = new ArrayList<>();
        //Open the file at specified location in read mode
        Scanner file;
        try {
            file = new Scanner(new File(filePath));

            file.useDelimiter("\n");

            //Process each record in csv file, create an object of desired type and add it to transactions array
            while (file.hasNext()){
                String line = file.next();
                String[] words = line.split(",");

                String transactionID = words[0].trim();
                String fromAccountID = words[1].trim();
                String toAccoundID = words[2].trim();
                String dateTimeString = words[3].trim();
                LocalDateTime createdAt = LocalDateTime.parse(dateTimeString.trim(), format);
                double amount = Double.parseDouble(words[4].trim());

                if (words[5].trim().compareTo("PAYMENT")==0){
                    Payment p = new Payment(transactionID, fromAccountID, toAccoundID, createdAt, amount);
                    transactions.add(p);
                }

                else {
                    String reverseTransactionID = words[6].trim();
                    Reversal r = new Reversal(transactionID, fromAccountID, toAccoundID, createdAt, amount, reverseTransactionID);
                    transactions.add(r);

                    for (int i = 0; i < transactions.size(); i++) {
                        Transaction t = transactions.get(i);
                        if (t.getTransactionID().compareTo(reverseTransactionID)==0 && (t instanceof Payment)){
                            Payment p = (Payment) t;
                            p.setHasReversal(true);
                        }
                    }
                }

            }

            //end of processing transactions and loading them into program memory
            this.transactions = transactions;

            System.out.println("Size" + this.transactions.size());
        } catch (Exception e) {
            System.out.println("File not found");
            throw new Exception("Exception occured");
        }
    }

}
