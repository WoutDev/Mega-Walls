package be.woutdev.megawalls.api.handler;

import java.util.List;

/**
 * Created by Wout on 20/08/2016.
 */
public interface OfflineHandler<T>
{
    T insert(T object);

    T update(T object);

    void delete(T object);

    List<T> findAll();
}
