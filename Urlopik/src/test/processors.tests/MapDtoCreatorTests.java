import org.junit.Assert;
import org.junit.Test;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MapDtoCreatorTests {

    @Test
    public void shouldCreateEmptyMap(){
        Map<LocalDate,OffMode> map = new TreeMap<>();
        MapDto dto = new MapDto();
        MapDtoCreator creator = new MapDtoCreator();

        creator.serialize(map,dto);
        Assert.assertTrue(dto.yearMapDto.isEmpty());
    }

    @Test
    public void shouldCreateProperDto(){
    Map<LocalDate,OffMode> map = new TreeMap<>();
    map.put(LocalDate.parse("2019-09-12"),OffMode.WORKING);
    map.put(LocalDate.parse("2019-09-13"),OffMode.OFF);
    map.put(LocalDate.parse("2019-09-14"),OffMode.HOLIDAY);

    MapDto dto = new MapDto();
    MapDtoCreator creator = new MapDtoCreator();

    creator.serialize(map,dto);
    Object[] dtoKeySet = dto.yearMapDto.keySet().toArray();
        Assert.assertEquals(dtoKeySet[0].toString(),"2019-09-12");
        Assert.assertEquals(dtoKeySet[1].toString(),"2019-09-13");
        Assert.assertEquals(dtoKeySet[2].toString(),"2019-09-14");

    Object[] dtoValueSet = dto.yearMapDto.values().toArray();
        Assert.assertEquals(dtoValueSet[0].toString(),"WORKING");
        Assert.assertEquals(dtoValueSet[1].toString(),"OFF");
        Assert.assertEquals(dtoValueSet[2].toString(),"HOLIDAY");
    }
}
