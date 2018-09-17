package io.github.genelist.listitems;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import de.umass.lastfm.Caller;
import de.umass.lastfm.Artist;

import io.github.genelist.util.Constants;
import io.github.genelist.util.ImageItem;

public class MusicArtist extends ListItem {
    private String id = ListItem.DEFAULT_ID;
    private String name = "";
    private ImageItem image = new ImageItem();
    private Artist artist;

    public MusicArtist() { super(); }

    public MusicArtist(String id, String name, ImageItem image, Artist artist) {
        exists = true;
        this.id = id;
        this.name = name;
        this.image = image;
        this.artist = artist;
        Caller.getInstance().setUserAgent(Constants.LASTFM_USER);
    }

    public static List<MusicArtist> search(String name) {
        List<Artist> artists = new ArrayList<>(Artist.search(name, Constants.LASTFM_API_KEY));
        return artists.stream().map(MusicArtist::fromArtist).collect(Collectors.toList());
    }

    public static MusicArtist getById(String id) {
        try {
            Artist a = Artist.getInfo(id, Locale.US, null, Constants.LASTFM_API_KEY);
            return new MusicArtist(a.getMbid(), a.getName(), new ImageItem(), a);
        } catch (Exception e) {
            return new MusicArtist();
        }
    }

    public static MusicArtist getByName(String name) {
        try {
            Artist a = Artist.getInfo(name, Locale.US, null, Constants.LASTFM_API_KEY);
            return new MusicArtist(a.getMbid(), a.getName(), new ImageItem(), a);
        } catch (Exception e) {
            return new MusicArtist();
        }
    }

    public List<MusicArtist> getSimilar() {
        List<Artist> similar = new ArrayList<>(artist.getSimilar());
        return similar.stream().map(MusicArtist::fromArtist).collect(Collectors.toList());
    }

    private static MusicArtist fromArtist(Artist a) {
        return new MusicArtist(a.getMbid(), a.getName(), new ImageItem(),
                Artist.getInfo(a.getMbid(), Locale.US, null, Constants.LASTFM_API_KEY));
    }

    @Override
    public String getId() { return id; }

    @Override
    public long getLongId() {
        StringBuilder newId = new StringBuilder();
        String[] parts = id.split("-");
        for (String part : parts) {
            newId.append(Long.decode("0x" + part));
        }
        return Long.parseLong(newId.toString());
    }

    public String getName() { return name; }

    @Override
    public ImageItem getImage() { return image; }

    public Artist getArtist() { return artist; }
}
