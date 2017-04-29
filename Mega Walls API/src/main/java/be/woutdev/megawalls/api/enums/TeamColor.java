package be.woutdev.megawalls.api.enums;

/**
 * Created by Wout on 19/08/2016.
 */
public enum TeamColor
{
    GREEN, BLUE, YELLOW, RED, PURPLE, CYAN;

    public static TeamColor findByName(String name)
    {
        for (TeamColor color : values())
        {
            if (color.name().equalsIgnoreCase(name))
            {
                return color;
            }
        }

        return null;
    }
}
