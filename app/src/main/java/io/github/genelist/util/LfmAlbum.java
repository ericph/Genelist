package io.github.genelist.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import de.umass.lastfm.Album;
import de.umass.lastfm.ImageSize;
import de.umass.lastfm.Result;
import de.umass.lastfm.Session;
import de.umass.lastfm.Tag;
import de.umass.lastfm.Track;

public class LfmAlbum {
    private Album album;

    public LfmAlbum(Album album) { this.album = album; }

    public String getMbid() { return album.getMbid(); }

    public String getName() { return album.getName(); }

    public String getArtist() { return album.getArtist(); }

    public String getImageURL(ImageSize size) { return album.getImageURL(size); }

    public Date getReleaseDate() { return album.getReleaseDate(); }

    public Collection<Track> getTracks() { return album.getTracks(); }

    public static LfmAlbum getInfo(String artist, String albumOrMbid, String apiKey) {
        return new LfmAlbum(Album.getInfo(artist, albumOrMbid, apiKey));
    }

    public static LfmAlbum getInfo(String artist, String albumOrMbid, String username, String apiKey) {
        return new LfmAlbum(Album.getInfo(artist, albumOrMbid, username, apiKey));
    }

    public static Result addTags(String artist, String album, String tags, Session session) {
        return Album.addTags(artist, album, tags, session);
    }

    public static Result removeTag(String artist, String album, String tag, Session session) {
        return Album.removeTag(artist, album, tag, session);
    }

    public static Collection<String> getTags(String artist, String album, Session session) {
        return Album.getTags(artist, album, session);
    }

    public static Collection<Tag> getTopTags(String artist, String albumOrMbid, String apiKey) {
        return Album.getTopTags(artist, albumOrMbid, apiKey);
    }

    public static Collection<LfmAlbum> search(String album, String apiKey) {
        List<Album> albums = new ArrayList<>(Album.search(album, apiKey));
        return albums.stream().map(LfmAlbum::new).collect(Collectors.toList());
    }
}
