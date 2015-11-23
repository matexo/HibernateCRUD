/**
 * Created by Matexo on 2015-11-17.
 */
public interface CRUDInterface<T> {
    Long create(T item);
    T read(Long id);
    void update(Long id , T item);
    void delete(Long id);
}
