package be.woutdev.megawalls.plugin.data.ebean;

import be.woutdev.megawalls.api.MWAPI;
import be.woutdev.megawalls.api.model.Team;
import be.woutdev.megawalls.plugin.model.BasicTeam;
import com.avaje.ebean.config.ScalarTypeConverter;

/**
 * Created by Wout on 20/08/2016.
 */
public class TeamScalarTypeConverter implements ScalarTypeConverter<Team, Integer>
{
    @Override
    public Team getNullValue()
    {
        return null;
    }

    @Override
    public Team wrapValue(Integer scalarType)
    {
        return MWAPI.getTeamHandler().findById(scalarType);
    }

    @Override
    public Integer unwrapValue(Team beanType)
    {
        return ((BasicTeam) beanType).getId();
    }
}
