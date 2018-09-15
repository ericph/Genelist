package io.github.genelist.lists;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Collections;

import io.github.genelist.R;
import io.github.genelist.listitems.ListItem;
import io.github.genelist.util.Constants;

public class GeneList<K extends ListItem> extends ArrayList<K>{
    public static final int MAX_SIZE = 5;

    public GeneList() {
        super(MAX_SIZE);
    }

    public GeneList(K... items) {
        super(MAX_SIZE);
        if (items.length <= MAX_SIZE)
            Collections.addAll(this, items);
        else
            warn(R.string.err_full_list);
    }

    @Override
    public boolean add(K item) {
        if (size() < MAX_SIZE)
            return super.add(item);
        warn(R.string.err_full_list);
        return false;
    }

    @Override
    public void add(int index, K item) {
        if (size() < MAX_SIZE)
            super.add(index, item);
        warn(R.string.err_full_list);
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
        warn(R.string.err_list_index);
        return false;
    }

    private void warn(int msgVal) {
        String msg = Resources.getSystem().getString(msgVal);
        // TODO: trigger some sort of warning based on the given msg
    }

    public long getId() {
        return Constants.LIST_ID_MAP.get(getClass().getComponentType());
    }

    public K _1() { return get(0); }
    public K _2() { return get(1); }
    public K _3() { return get(2); }
    public K _4() { return get(3); }
    public K _5() { return get(4); }
}
