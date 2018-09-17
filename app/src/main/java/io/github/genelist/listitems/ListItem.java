package io.github.genelist.listitems;

import io.github.genelist.util.ImageItem;

public abstract class ListItem {
    public static final String DEFAULT_ID = "";

    public boolean exists;

    protected ListItem() {
        exists = false;
    }

    public abstract String getId();

    public abstract long getLongId();

    public ImageItem getImage() { return new ImageItem(); }
}
