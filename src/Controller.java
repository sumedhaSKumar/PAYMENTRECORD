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
        } catch (Exception e) {
            System.out.println("File not found");
            throw new Exception("Exception occured");
        }
    }

    public double[] calculateTotals( String selectedAccountID, LocalDateTime startTime, LocalDateTime endTime){
        ArrayList<Transaction> transactions = this.transactions;
        // Initialize counter and summation variable
        double sum = 0;
        int count = 0;

        for (int i = 0; i<transactions.size(); i++) {
            Transaction t = transactions.get(i);
            //Check if the given account is in from account and transaction is happening within concerned time period.
            if (t.getFromAccountID().compareTo(selectedAccountID)==0 && t.getCreatedAt().isAfter(startTime) && t.getCreatedAt().isBefore(endTime)){
                //If yes, check only for payments and ignore reversals
                if (t instanceof Payment) {
                    Payment p = (Payment) t;
                    //If yes, ignore all payments that have a reversal and pick only those that dont have a reversal.
                    if (p.isHasReversal()==false) {
                        //Since the account is in from account it goes to negative balance.
                        sum -= p.getAmount();
                        count++;
                    }
                }
            }
            //Check if the given account is in to account and transaction is happening within concerned time period.
            else if (t.getToAccoundID().compareTo(selectedAccountID)==0 && t.getCreatedAt().isAfter(startTime) && t.getCreatedAt().isBefore(endTime)){
                //If yes, check only for payments and ignore reversals
                if (t instanceof Payment) {
                    Payment p = (Payment) t;
                    //If yes, ignore all payments that have a reversal and pick only those that dont have a reversal.
                    if (p.isHasReversal()==false) {
                        //Since the account is in to account it goes to positive balance.
                        sum += p.getAmount();
                        count++;
                    }
                }
            }
        }

        System.out.println("The total amount transacted on this account between specified period is: " + sum );
        System.out.println("The total number of transactions on this account within specified period is: " + count);

        return new double[]{sum, count};
    }

}
