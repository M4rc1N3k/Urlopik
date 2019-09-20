import java.time.LocalDate;
import java.util.Map;
import java.io.*;

public interface ISerializer <T> {
    T serialize(IMap map, T returnedObject);
}
