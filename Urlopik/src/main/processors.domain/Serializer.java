import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

public class Serializer implements ISerializer<File> {

    @Override
    public File serialize(IMap map, File file) {
        Map<LocalDate,OffMode> yearMap = map.getMap();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, yearMap);
            //consider string output later turn to JSON
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return file;
        }
    }
}
