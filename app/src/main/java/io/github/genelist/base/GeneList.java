package io.github.genelist.base;

import android.graphics.drawable.VectorDrawable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

import io.github.genelist.R;
import io.github.genelist.util.Constants;
import io.github.genelist.util.Util;

public class GeneList<K extends ListItem> extends ArrayList<K>{
    private static final Logger LOG = Logger.getLogger("io.github.genelist.lists.GeneList");
    private static final int MAX_SIZE = 5;

    private VectorDrawable icon;

    public GeneList() {
        super(MAX_SIZE);
    }

    @SafeVarargs
    public GeneList(K... items) {
        super(MAX_SIZE);
        if (items.length <= MAX_SIZE)
            Collections.addAll(this, items);
        else
            Util.warn(LOG, R.string.err_full_list);
    }

    @Override
    public boolean add(K item) {
        if (size() < MAX_SIZE)
            return super.add(item);
        Util.warn(LOG, R.string.err_full_list);
        return false;
    }

    @Override
    public void add(int index, K item) {
        if (size() < MAX_SIZE)
            super.add(index, item);
        Util.warn(LOG, R.string.err_full_list);
    }

    public boolean reorder(int itemIndex, int destIndex) {
        boolean success = validateIndex(itemIndex) && validateIndex(destIndex);
        if (success)
            add(destIndex, remove(itemIndex));
        return success;
    }

    private boolean validateIndex(int index) {
        if (index >= 0 && index < MAX_SIZE)
            return true;
        Util.warn(LOG, R.string.err_list_index);
        return false;
    }

    public void setIcon(VectorDrawable icon) { this.icon = icon; }

    public String getId() {
        return Constants.LIST_ID_MAP.get(getClass().getComponentType());
    }

    public VectorDrawable getIcon() { return icon; }

    public K _1() { return get(0); }
    public K _2() { return get(1); }
    public K _3() { return get(2); }
    public K _4() { return get(3); }
    public K _5() { return get(4); }
}
