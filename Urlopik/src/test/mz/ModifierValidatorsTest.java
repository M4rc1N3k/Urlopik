package test.mz;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import main.mz.Modifier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Map;

import static org.junit.Assert.*;

public class ModifierValidatorsTest {

    int remaining = 260;
    int planned = 0;
    OperationMode mode = OperationMode.SET;
    int year = 2018;

    Method av = allowanceValidatorReflector();
    Method dv = dateValidatorReflector();

    File file = new File ("src//test//resources//off_clear.txt");
    String offBinary;


    //offBinary = Files.asCharSource(file, Charsets.UTF_8).read();
    Modifier modifier;
    Map<LocalDate, OffMode> curYearMap;
    //Map<LocalDate, OffMode> curYearMap = Mapper.generateMap(Reader.dataFromFile("src//test//resources//off_clear.txt"), year);

    @Before
    public void setUp() {
        try{
            offBinary = Files.asCharSource(file, Charsets.UTF_8).read();
        }catch (IOException e){
            System.exit(1);
        }
        curYearMap = new Mapper(offBinary, year).getYearMap();
        modifier = new Modifier(mode, remaining, planned, curYearMap);
    }

    @After
    public void tearDown() {
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
                    validAllowance = (Boolean)av.invoke(modifier,mode,curYearMap,start,end,remaining);
                    validDate = (Boolean)dv.invoke(modifier,start,end);
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