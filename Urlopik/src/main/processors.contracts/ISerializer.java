import java.time.LocalDate;
import java.util.Map;
import java.io.*;

public interface ISerializer {
    void serialize(Map<LocalDate, OffMode> yearMap, File file);
}
