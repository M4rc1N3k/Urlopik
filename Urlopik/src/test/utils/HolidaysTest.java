import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
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
        ec = easterCalcReflector();
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

            tabbedDate = LocalDate.parse(verificationFileScanner.nextLine());

            try {
                calcDate = (LocalDate)ec.invoke(null,i);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }

            assertTrue(tabbedDate.isEqual(calcDate));

        }
    }


    public Method easterCalcReflector(){

        Method ec = null;
        try {
            ec = Holidays.class.getDeclaredMethod("easterCalculator", int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        ec.setAccessible(true);

        return ec;

    }
}