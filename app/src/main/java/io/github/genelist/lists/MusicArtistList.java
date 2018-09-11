package io.github.genelist.lists;

import io.github.genelist.listitems.MusicArtist;

public class MusicArtistList extends GeneList<MusicArtist> {

    public MusicArtistList() {
        super();
    }

    public MusicArtist search(String name) {
        return new MusicArtist();
    }
}
