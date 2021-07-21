import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestController {
    @Test
    void testCalculateTotalsForAccountWithReversalTimeBound() {
        // Fixtures
        String path = "src/Static/transactions.csv";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        Controller controller = new Controller();
        try {
            controller.processCSVData(path, format);
            String selectedAccountID = "ACC998877";
            LocalDateTime startDate = LocalDateTime.parse("20/10/2018 12:00:00", format);
            LocalDateTime endDate = LocalDateTime.parse("20/10/2018 20:00:00", format);


            //Actions
            double[] result = controller.calculateTotals(selectedAccountID, startDate, endDate);

            //Actual results
            double sum = result[0];
            int count = (int)result[1];

            //Expected results
            double expectedSum = 2.0;
            int expectedCount = 2;

            //Assert
            assertEquals(sum, expectedSum);
            assertEquals(count, expectedCount);

        }
        catch (Exception e) {

        }
    }

    @Test
    void testCalculateTotalsForAccountWithNoReversalTimeBound() {
        // Fixtures
        String path = "src/Static/transactions.csv";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        Controller controller = new Controller();
        try {
            controller.processCSVData(path, format);
            String selectedAccountID = "ACC778899";
            LocalDateTime startDate = LocalDateTime.parse("20/10/2018 12:00:00", format);
            LocalDateTime endDate = LocalDateTime.parse("20/10/2018 22:00:00", format);


            //Actions
            double[] result = controller.calculateTotals(selectedAccountID, startDate, endDate);

            //Actual results
            double sum = result[0];
            int count = (int)result[1];

            //Expected results
            double expectedSum = 28.0;
            int expectedCount = 4;

            //Assert
            assertEquals(sum, expectedSum);
            assertEquals(count, expectedCount);

        }
        catch (Exception e) {

        }
    }

    @Test
    void testCalculateTotalsForAccountWithDifferentTimeBound() {
        // Fixtures
        String path = "src/Static/transactions.csv";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        Controller controller = new Controller();
        try {
            controller.processCSVData(path, format);
            String selectedAccountID = "ACC778899";
            LocalDateTime startDate = LocalDateTime.parse("20/10/2018 12:00:00", format);
            LocalDateTime endDate = LocalDateTime.parse("21/10/2018 22:00:00", format);


            //Actions
            double[] result = controller.calculateTotals(selectedAccountID, startDate, endDate);

            //Actual results
            double sum = result[0];
            int count = (int)result[1];

            //Expected results
            double expectedSum = 35.25;
            int expectedCount = 5;

            //Assert
            assertEquals(sum, expectedSum);
            assertEquals(count, expectedCount);

        }
        catch (Exception e) {

        }
    }


    @Test
    void testProcessCSVDataWhenValidFilePAthProvided() {
        // Fixtures
        String path = "src/Static/transactions.csv";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        Controller controller = new Controller();

        //Actions
        try {
            controller.processCSVData(path, format);

            //Actual results
            int size = controller.transactions.size();

            //Expected results
            int expectedSize = 7;

            //Assertions
            assertEquals(size, expectedSize);
        } catch (Exception e){

        }
    }

    @Test
    void testProcessCSVDataWhenInvalidFilePathProvided() {
        // Fixtures
        String path = "InvalidPath/Static/transactions.csv";
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        Controller controller = new Controller();

        //Actions
        try {
            controller.processCSVData(path, format);


        } catch (Exception e){
            //Expected results
            String expectedExceptionMessage = "Exception occured";

            //Expected results are e
            String actualExceptionMessage = e.getMessage();
            //Assertions
            assertEquals(actualExceptionMessage, expectedExceptionMessage);
        }
    }
}
