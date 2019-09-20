import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class MapDtoCreator implements ISerializer<MapDto> {
    @Override
    public MapDto serialize(IMap map, MapDto resultObject) {
        Map<LocalDate,OffMode> yearMap = map.getMap();
        resultObject.yearMapDto = new TreeMap<>();

        for (Map.Entry<LocalDate,OffMode> record: yearMap.entrySet())
        {
            resultObject.yearMapDto.put(
                    record.getKey().toString(),
                    record.getValue().toString());
        }
        return resultObject;
    }
}
