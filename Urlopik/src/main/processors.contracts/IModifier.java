import java.time.LocalDate;
import java.util.Map;

public interface IModifier {
    public Map<LocalDate, OffMode> getModifiedYearMap();
}
