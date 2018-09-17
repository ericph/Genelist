package io.github.genelist.util;

import de.umass.lastfm.Artist;
import de.umass.lastfm.ImageSize;

public class ImageItem {
    private boolean hasImage;
    private String url = "";

    public ImageItem() {
        hasImage = false;
    }

    public ImageItem(String url) {
        hasImage = true;
        this.url = url;
    }

    public static ImageItem fromArtist(Artist artist) {
        return new ImageItem(artist.getImageURL(ImageSize.SMALL));
    }

    public boolean hasImage() { return hasImage; }

    public String getUrl() { return url; }
}
