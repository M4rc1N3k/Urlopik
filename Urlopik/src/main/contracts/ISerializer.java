import java.time.LocalDate;
import java.util.Map;

public interface ISerializer {
    String serialize(Map<LocalDate, OffMode> yearMap);
}
