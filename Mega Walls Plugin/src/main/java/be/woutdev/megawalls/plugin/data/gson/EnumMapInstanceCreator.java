package be.woutdev.megawalls.plugin.data.gson;

import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;
import java.util.EnumMap;

/**
 * Created by Wout on 21/08/2016.
 */
public class EnumMapInstanceCreator<K extends Enum<K>, V> implements InstanceCreator<EnumMap<K, V>>
{
    private final Class<K> enumClass;

    public EnumMapInstanceCreator(Class<K> enumClass)
    {
        this.enumClass = enumClass;
    }

    @Override
    public EnumMap<K, V> createInstance(Type type)
    {
        return new EnumMap<K, V>(enumClass);
    }
}
