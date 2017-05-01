package be.woutdev.megawalls.plugin.gui;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by Wout on 30/04/2017.
 */
public class GuiBuilder
{
    private final String name;
    private String title;
    private Set<GuiItem> items;

    public GuiBuilder(String name)
    {
        this.name = name;
        this.title = "Graphical User Interface";
        this.items = Sets.newHashSet();
    }

    public GuiBuilder title(String title)
    {
        this.title = title;

        return this;
    }

    public GuiBuilder addItem(GuiItem item)
    {
        this.items.add(item);

        return this;
    }

    public Gui create()
    {
        return new Gui(name, title, items);
    }
}
