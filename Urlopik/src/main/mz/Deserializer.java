import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

public class Deserializer implements IMapper {

    private String offBinary;
    private int year;

    public Deserializer(String offBinary, int year) {
        this.offBinary = offBinary;
        this.year = year;
    }

    @Override
    public Map<LocalDate, OffMode> getYearMap() {
        return generateYearMap(offBinary,year);
    }

    public Map<LocalDate, OffMode> generateYearMap(String offBinary, int year) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(offBinary, new TypeReference<Map<LocalDate, OffMode>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
