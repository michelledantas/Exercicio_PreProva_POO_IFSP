import java.util.List;

public interface DAO <K, V>{

    V findOne(K key);
    List<V> findAll();
    boolean delete (K key);
    boolean save (V value);
    boolean update(V value);
}

