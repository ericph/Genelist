package io.github.genelist.listitems;

import io.github.genelist.util.ImageItem;

public class MusicArtist extends ListItem {
    private long id = ListItem.DEFAULT_ID;
    private String name = "";
    private ImageItem image = new ImageItem();

    public MusicArtist() { super(); }

    public MusicArtist(long id, String name, ImageItem image) {
        exists = true;
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public static MusicArtist search(String query) {
        // TODO: implement search
        return new MusicArtist();
    }

    public static MusicArtist getById(long id) {
        // TODO: implement getById
        return new MusicArtist();
    }

    @Override
    public long getId() { return id; }

    public String getName() { return name; }

    public ImageItem getImage() { return image; }
}
