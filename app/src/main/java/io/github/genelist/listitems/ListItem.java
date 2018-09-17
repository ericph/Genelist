package io.github.genelist.listitems;

public abstract class ListItem {
    public static final String DEFAULT_ID = "";

    public boolean exists;

    protected ListItem() {
        exists = false;
    }

    public abstract String getId();
}
