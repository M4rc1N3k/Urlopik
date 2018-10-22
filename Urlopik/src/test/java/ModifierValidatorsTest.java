import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Map;

import static org.junit.Assert.*;

public class ModifierValidatorsTest {

    int remaining = 260;
    int planned = 0;
    OperationMode mode;
    int year = 2018;

    Method av = allowanceValidatorReflector();
    Method dv = dateValidatorReflector();

    Map<LocalDate, OffMode> curYearMap = Mapper.generateMap(Reader.dataFromFile("src//test//resources//off_clear.txt"), year);

    @Before
    public void setUp() throws Exception {


    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void changeOff() {
     LocalDate start, end;

        for (int i = 1; i <= 365; i++){
            for (int j = i; j <= 365; j++){
                start = LocalDate.ofYearDay(year, i);
                end = LocalDate.ofYearDay(year, j);

                Boolean validAllowance = null;
                Boolean validDate = null;

                try {
                    validAllowance = (Boolean)av.invoke(null,mode,curYearMap,start,end,remaining);
                    validDate = (Boolean)dv.invoke(null,start,end);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }

                assertTrue(validAllowance);
                assertTrue(validDate);
            }
        }
    }

    public Method allowanceValidatorReflector(){

        Method av = null;
        try {
            av = Modifier.class.getDeclaredMethod("allowanceValidator", OperationMode.class, Map.class, LocalDate.class, LocalDate.class, int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        av.setAccessible(true);

        return av;

    }

    public Method dateValidatorReflector(){

        Method dv = null;

        try {
            dv = Modifier.class.getDeclaredMethod("dateValidator", LocalDate.class, LocalDate.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        dv.setAccessible(true);

        return dv;
    }
}