import java.time.LocalDate;
import java.util.Map;

public class MapDtoCreator implements ISerializer<MapDto> {
    @Override
    public MapDto serialize(IMap map, MapDto resultObject) {
        var yearMap = map.getMap();
        for (Map.Entry<LocalDate, OffMode> record: yearMap.entrySet())
        {
            resultObject.yearMapDto.put(
                    record.getKey().toString(),
                    record.getValue().toString());
        }
        return resultObject;
    }
}
