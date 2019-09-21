public interface ISerializer <T> {
    T serialize(IMap map, T returnedObject);
}
