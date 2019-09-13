import java.time.LocalDate;
import java.util.Map;

public interface IMapper {
    Map<LocalDate, OffMode> getYearMap();
}
