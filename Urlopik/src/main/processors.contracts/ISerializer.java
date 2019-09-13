import java.time.LocalDate;
import java.util.Map;
import java.io.*;

public interface ISerializer <T> {
    void serialize(Map<LocalDate, OffMode> yearMap, T resultObject);
}
