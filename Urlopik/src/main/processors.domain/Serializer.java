import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

public class Serializer implements ISerializer<File> {

    @Override
    public void serialize(Map<LocalDate, OffMode> yearMap, File file) {
        ObjectMapper objectMapper = new ObjectMapper(   );
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, yearMap);
            //consider string output later turn to JSON
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
