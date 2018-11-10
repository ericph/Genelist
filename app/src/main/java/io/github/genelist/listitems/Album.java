package io.github.genelist.listitems;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.umass.lastfm.Caller;
import de.umass.lastfm.ImageSize;

import io.github.genelist.base.ImageItem;
import io.github.genelist.base.ListItem;
import io.github.genelist.util.Constants;
import io.github.genelist.util.LfmAlbum;
import io.github.genelist.util.Util;

public class Album extends ListItem {
    private String id = ListItem.DEFAULT_ID;
    private String name = "";
    private ImageItem image = new ImageItem();
    private LfmAlbum album;
    private String musician;

    public Album() { super(); }

    public Album(String id, String name, ImageItem image, LfmAlbum album, String musician) {
        exists = true;
        this.id = id;
        this.name = name;
        this.image = image;
        this.album = album;
        this.musician = musician;
        Caller.getInstance().setUserAgent(Constants.LASTFM_USER);
    }

    public static List<Album> search(String name) {
        List<LfmAlbum> albums = new ArrayList<>(LfmAlbum.search(name, Constants.LASTFM_API_KEY));
        return albums.stream().map(Album::fromLfmAlbum).collect(Collectors.toList());
    }

    public static Album getById(String artistName, String albumId) {
        try {
            LfmAlbum a = LfmAlbum.getInfo(artistName, albumId, Constants.LASTFM_API_KEY);
            ImageItem image = new ImageItem(a.getImageURL(ImageSize.SMALL));
            return new Album(a.getMbid(), a.getName(), image, a, a.getArtist());
        } catch (Exception e) {
            return new Album();
        }
    }

    public static Album getByName(String artistName, String albumName) {
        try {
            LfmAlbum a = LfmAlbum.getInfo(artistName, albumName, Constants.LASTFM_API_KEY);
            ImageItem image = new ImageItem(a.getImageURL(ImageSize.SMALL));
            return new Album(a.getMbid(), a.getName(), image, a, a.getArtist());
        } catch (Exception e) {
            return new Album();
        }
    }

    private static Album fromLfmAlbum(LfmAlbum album) {
        LfmAlbum a =  LfmAlbum.getInfo(album.getArtist(), album.getMbid(), Constants.LASTFM_API_KEY);
        ImageItem image = new ImageItem(a.getImageURL(ImageSize.SMALL));
        return new Album(a.getMbid(), a.getName(), image, a, a.getArtist());
    }

    @Override
    public String getId() { return id; }

    @Override
    public long getLongId() { return Util.mbidToLong(id); }

    public String getName() { return name; }

    @Override
    public ImageItem getImage() { return image; }

    public LfmAlbum getLfmAlbum() { return album; }

    public Musician getMusician() { return Musician.getByName(musician); }
}
