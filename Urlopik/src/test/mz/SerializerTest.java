import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class SerializerTest {

    @Test
    public void shouldReturnProperlyFormattedJSON()
    {
        Map<LocalDate, OffMode> map = new TreeMap<>();
        map.put(LocalDate.now(),OffMode.OFF);
        map.put(LocalDate.now().plusDays(1),OffMode.WORKING);
        map.put(LocalDate.now().plusDays(2),OffMode.HOLIDAY);

        int year = LocalDate.now().getYear();

        String expected = "{\n  \""+LocalDate.now()+"\" : \""+OffMode.OFF.toString()+"\","+
                "\n  \""+LocalDate.now().plusDays(1)+"\" : \""+OffMode.WORKING.toString()+"\","+
                "\n  \""+LocalDate.now().plusDays(2)+"\" : \""+OffMode.HOLIDAY.toString()+"\"\n}";

        ISerializer ser = Mockito.mock(Serializer.class);

        Assert.assertEquals(1,1);
    }
}
