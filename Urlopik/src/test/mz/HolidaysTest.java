import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public class HolidaysTest {

    private Scanner verificationFileScanner;
    private Method ec;


    @Before
    public void setUp(){
        verificationFileScanner=null;
        String dir = new StringBuilder().append("src//test//resources//easter.txt").toString();

        try {
            verificationFileScanner = new Scanner(new FileReader(dir));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @After
    public void cleanUp(){
        verificationFileScanner.close();
    }

    @Test
    public void thisYearEasterCheck(){

        for (int i = 2001; i <= 2100; i++){

            LocalDate tabbedDate, calcDate=null;
            Holidays holidays = new Holidays(i);

            tabbedDate = LocalDate.parse(verificationFileScanner.nextLine());

            calcDate = holidays.easterCalculator();

            assertTrue(tabbedDate.isEqual(calcDate));
        }
    }
}