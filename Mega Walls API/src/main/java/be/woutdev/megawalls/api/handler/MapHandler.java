package be.woutdev.megawalls.api.handler;

import be.woutdev.megawalls.api.model.Map;

import java.util.Set;

/**
 * Created by Wout on 19/08/2016.
 */
public interface MapHandler
{
    boolean create(String name);

    boolean delete(String name);

    Map findByName(String name);

    boolean exists(String name);

    Set<Map> findAll();
}
