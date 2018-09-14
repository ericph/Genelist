package io.github.genelist.listitems;

import io.github.genelist.lists.GeneList;

public class GeneListItem extends ListItem {
    private long id;
    private GeneList list;

    public GeneListItem(GeneList list) {
        exists = true;
        this.id = list.getId();
        this.list = list;
    }

    @Override
    public long getId() { return id; }

    public GeneList getList() { return list; }
}
