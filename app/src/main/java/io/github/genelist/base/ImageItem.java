package io.github.genelist.base;

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

    public boolean hasImage() { return hasImage; }

    public String getUrl() { return url; }
}
