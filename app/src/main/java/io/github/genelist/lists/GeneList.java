package io.github.genelist.lists;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import io.github.genelist.listitems.ListItem;

public class GeneList<K extends ListItem> extends ArrayList<K>{
    public static final int MAX_SIZE = 5;

    private long id;
    private Context context;

    public GeneList() {
        super(MAX_SIZE);
    }

    public GeneList(Context context) {
        super(MAX_SIZE);
        this.context = context;
    }

    @Override
    public boolean add(K item) {
        if (size() < MAX_SIZE)
            return super.add(item);
        warn("Cannot add to a full list!");
        return false;
    }

    @Override
    public void add(int index, K item) {
        if (size() < MAX_SIZE)
            super.add(index, item);
        warn("Cannot add to a full list!");
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
        warn("Invalid list index!");
        return false;
    }

    private void warn(String msg) {
        if (context != null)
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public long getId() { return id; }

    public K _1() { return get(0); }
    public K _2() { return get(1); }
    public K _3() { return get(2); }
    public K _4() { return get(3); }
    public K _5() { return get(4); }
}
