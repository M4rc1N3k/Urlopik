import java.time.LocalDate;
import java.util.Map;

public class OffMap implements IMap {

    private Map<LocalDate, OffMode> map;

    public OffMap(Map<LocalDate, OffMode> map) {
        this.map = map;
    }

    @Override
    public Map<LocalDate, OffMode> getMap() {
        return map;
    }
}
