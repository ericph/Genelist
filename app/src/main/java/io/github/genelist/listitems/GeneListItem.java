package io.github.genelist.listitems;

import io.github.genelist.lists.GeneList;

public class GeneListItem extends ListItem {
    private String id;
    private GeneList list;

    public GeneListItem(GeneList list) {
        exists = true;
        this.id = list.getId();
        this.list = list;
    }

    @Override
    public String getId() { return id; }

    @Override
    public long getLongId() { return Long.parseLong(id); }

    public GeneList getList() { return list; }
}
