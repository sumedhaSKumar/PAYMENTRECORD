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



    }

    }
