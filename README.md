# PAYMENTRECORD
Readme file

This repository contains a calculator to calculate the relative balance of a given account ID in a given start and end time by considering reversed transactions also.

**Repository**
This git repository has branches based on the agile TRELLO board: https://trello.com/invite/b/XjcOXsxT/d472fac56ce0e14ced87da795a83251c/transaction-record
Each branch represents each story card on the trello board for which the link has been provided above. It also includes a class diagram for better understanding.

**Prerequisites**
There needs to be a JDK 1.8 or higher version install to build and run this program locally

**Steps to run**

This folder contains Java bytecode that can be executed on the JVM and can be run directly on CMD: Follow the below commands
1. Download the JAVA.CLASSES folder
1. open CMD
2. Type **java Starter**
 By following above steps the program starts executing.
 
 Once the program starts executing the below message is printed:
 **Please enter the file path with file name exactly**
 Provide the path for CSV file, a CSV file has been added in JAVA.CLASSES folder called **transactions.csv** 
 Then enter the start time and end time one at a time.


**Design Model**

-> For the purpose of design I have used the model view controller model. The model is implemented with the help of three classes:
	1. Transaction: Parent class which is abstract because it need not be directly instantiable.
	2. Payment: This class inherits from Transaction class and has an additional instance variable of boolean type called "hasReversal". I added in this parameter so that It will be easier to process the transactions by omitting them directly if they ever have a reversal associated with them.
	3. Reversal:This class inherits from Transaction class and has an additional instance variable of String type called reversalTransactionID. It has been added to link a reversal with its corresponding payment.

-> The view is implemented using the Starter class which has only one main method with an object of the controller class. The view is responsible for taking user input of AccountID to be filtered, start time end time and location of csv file that contains the transaction records.

-> The controller is implemented by the Controller class and it has a single instance variable which is an ArrayList of Transaction type. This is permissible because we are not instantiating a Transaction directly using new keyword. We are instantiating either a Payment or a Reversal while processing csv and just adding it to the arraylist of type Transaction.





