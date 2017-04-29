package be.woutdev.megawalls.plugin.data.ebean;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.model.Map;
import com.avaje.ebean.config.ScalarTypeConverter;

/**
 * Created by Wout on 20/08/2016.
 */
public class MapScalarTypeConverter implements ScalarTypeConverter<Map, String>
{
    @Override
    public Map getNullValue()
    {
        return null;
    }

    @Override
    public Map wrapValue(String scalarType)
    {
        return MWAPI.getMapHandler().findByName(scalarType);
    }

    @Override
    public String unwrapValue(Map beanType)
    {
        return beanType.getName();
    }
}
