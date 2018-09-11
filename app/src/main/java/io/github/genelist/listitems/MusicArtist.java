package io.github.genelist.listitems;

import io.github.genelist.util.ImageItem;

public class MusicArtist extends ListItem {
    private String id = "";
    private String name = "";
    private ImageItem image = new ImageItem();

    public MusicArtist() { super(); }

    public MusicArtist(String id, String name, ImageItem image) {
        exists = true;
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public ImageItem getImage() { return image; }
}
