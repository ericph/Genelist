package io.github.genelist.listitems;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import de.umass.lastfm.Caller;
import de.umass.lastfm.Artist;

import io.github.genelist.base.ListItem;
import io.github.genelist.util.Constants;
import io.github.genelist.base.ImageItem;

public class Musician extends ListItem {
    private String id = ListItem.DEFAULT_ID;
    private String name = "";
    private ImageItem image = new ImageItem();
    private Artist artist;

    public Musician() { super(); }

    public Musician(String id, String name, ImageItem image, Artist artist) {
        exists = true;
        this.id = id;
        this.name = name;
        this.image = image;
        this.artist = artist;
        Caller.getInstance().setUserAgent(Constants.LASTFM_USER);
    }

    public static List<Musician> search(String name) {
        List<Artist> artists = new ArrayList<>(Artist.search(name, Constants.LASTFM_API_KEY));
        return artists.stream().map(Musician::fromArtist).collect(Collectors.toList());
    }

    public static Musician getById(String id) {
        try {
            Artist a = Artist.getInfo(id, Locale.US, null, Constants.LASTFM_API_KEY);
            return new Musician(a.getMbid(), a.getName(), ImageItem.fromArtist(a), a);
        } catch (Exception e) {
            return new Musician();
        }
    }

    public static Musician getByName(String name) {
        try {
            Artist a = Artist.getInfo(name, Locale.US, null, Constants.LASTFM_API_KEY);
            return new Musician(a.getMbid(), a.getName(), ImageItem.fromArtist(a), a);
        } catch (Exception e) {
            return new Musician();
        }
    }

    public List<Musician> getSimilar() {
        List<Artist> similar = new ArrayList<>(artist.getSimilar());
        return similar.stream().map(Musician::fromArtist).collect(Collectors.toList());
    }

    private static Musician fromArtist(Artist artist) {
        Artist a = Artist.getInfo(artist.getMbid(), Locale.US, null, Constants.LASTFM_API_KEY);
        return new Musician(a.getMbid(), a.getName(), ImageItem.fromArtist(a), a);
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
