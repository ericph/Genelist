package io.github.genelist.listitems;

public abstract class ListItem {
    public static final long DEFAULT_ID = -1;

    public boolean exists;

    protected ListItem() {
        exists = false;
    }

    public abstract long getId();
}
