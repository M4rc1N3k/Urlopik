import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

public class Serializer implements ISerializer {

    @Override
    public String serialize(Map<LocalDate, OffMode> yearMap) {
        ObjectMapper objectMapper = new ObjectMapper(   );
        try {
            objectMapper.writeValue(new File("target/off.json"), yearMap);
            //consider string output later turn to JSON
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
