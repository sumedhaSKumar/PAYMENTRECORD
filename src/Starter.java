import java.io.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;


public class Starter {

    public static void main(String[] args) throws Exception {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        Controller controller = new Controller();

        // Taking input for file location including file name
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the file path with file name exactly");
        String filePath = sc.nextLine();

        try {
            controller.processCSVData(filePath, format);
        }catch (Exception e) {
        }

        System.out.println("Please Enter account id for which you want to process records: ");
        String selectedAccountID = sc.nextLine();
        boolean f1 = !controller.accountExists(selectedAccountID);

        while (f1){
            System.out.println("Entered account does not exist, please enter a valid account number: ");
            selectedAccountID = sc.nextLine();
            f1 = !controller.accountExists(selectedAccountID);
        }

        System.out.println("Please enter the time from which the records should be processed: ");
        String startTimeString = sc.nextLine();
        boolean f2 = !(startTimeString.matches("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}"));
        while (f2){
            System.out.println("Please enter the time in DD/MM/YYYY HH:MM:SS format from which the records should be processed: ");
            startTimeString = sc.nextLine();
            f2 = !(startTimeString.matches("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}"));
        }
        LocalDateTime startTime = LocalDateTime.parse(startTimeString, format);

        System.out.println("Please enter the time to which the records should be processed: ");
        String endTimeString = sc.nextLine();
        boolean f3 = !(endTimeString.matches("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}"));
        while (f3){
            System.out.println("Please enter the time in DD/MM/YYYY HH:MM:SS format to which the records should be processed: ");
            endTimeString = sc.nextLine();
            f3 = !(endTimeString.matches("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}"));
        }
        LocalDateTime endTime = LocalDateTime.parse(endTimeString, format);

        if (endTime.isBefore(startTime)) {
            System.out.println("End time entered occurs before start time. This is invalid. Please try again.");
        }
        else {
            controller.calculateTotals( selectedAccountID, startTime, endTime);
        }

    }

    }
