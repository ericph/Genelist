package io.github.genelist.listitems;

import io.github.genelist.base.GeneList;
import io.github.genelist.base.ListItem;

public class GeneListItem extends ListItem {
    private String id;
    private GeneList<ListItem> list;

    public GeneListItem(GeneList<ListItem> list) {
        exists = true;
        this.id = list.getId();
        this.list = list;
    }

    @Override
    public String getId() { return id; }

    @Override
    public long getLongId() { return Long.parseLong(id); }

    public GeneList<ListItem> getList() { return list; }
}
