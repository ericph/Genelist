package io.github.genelist.lists;

import java.util.ArrayList;
import java.util.List;

import io.github.genelist.listitems.ListItem;

public class GeneList<K extends ListItem> {
    public static final int MAX_SIZE = 5;

    private List<K> list;

    public GeneList() {
        list = new ArrayList<>(MAX_SIZE);
    }

    public boolean add(K item) {
        return list.size() < MAX_SIZE && list.add(item);
    }

    public boolean contains(K item) {
        return list.contains(item);
    }

    public void clear() {
        list.clear();
    }

    public boolean remove(K item) {
        return list.remove(item);
    }

    public K remove(int index) {
        return list.remove(index);
    }

    public void reorder(int itemIndex, int destIndex) {
        K item = remove(itemIndex);
        if (item != null)
            list.add(destIndex, item);
    }

    public K _1() { return list.get(0); }
    public K _2() { return list.get(1); }
    public K _3() { return list.get(2); }
    public K _4() { return list.get(3); }
    public K _5() { return list.get(4); }
}
