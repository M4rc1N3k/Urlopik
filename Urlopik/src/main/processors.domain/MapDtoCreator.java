import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class MapDtoCreator implements ISerializer<MapDto> {
    @Override
    public void serialize(Map<LocalDate, OffMode> yearMap, MapDto resultObject) {

        resultObject.yearMapDto = new TreeMap<>();

        for (Map.Entry<LocalDate,OffMode> record: yearMap.entrySet())
        {
            resultObject.yearMapDto.put(
                    record.getKey().toString(),
                    record.getValue().toString());
        }
    }
}
