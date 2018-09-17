package io.github.genelist.listitems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import de.umass.lastfm.Caller;
import de.umass.lastfm.Artist;

import io.github.genelist.util.Constants;
import io.github.genelist.util.ImageItem;

public class MusicArtist extends ListItem {
    private String id = ListItem.DEFAULT_ID;
    private String name = "";
    private ImageItem image = new ImageItem();

    public MusicArtist() { super(); }

    public MusicArtist(String id, String name, ImageItem image) {
        exists = true;
        this.id = id;
        this.name = name;
        this.image = image;
        Caller.getInstance().setUserAgent(Constants.LASTFM_USER);
    }

    public static Collection<MusicArtist> search(String name) {
        List<Artist> artists = new ArrayList<>(Artist.search(name, Constants.LASTFM_API_KEY));
        return artists.stream()
                .map(a -> new MusicArtist(a.getId(), a.getName(), new ImageItem()))
                .collect(Collectors.toList());
    }

    public static MusicArtist getById(String id) {
        // TODO: implement getById
        return new MusicArtist();
    }

    @Override
    public String getId() { return id; }

    public String getName() { return name; }

    public ImageItem getImage() { return image; }
}
