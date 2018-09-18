package io.github.genelist.util;

import java.util.HashMap;
import java.util.Map;

import io.github.genelist.listitems.*;

public class Constants {
    public static final Map<Class, String> LIST_ID_MAP;
    static {
        LIST_ID_MAP = new HashMap<>();
        LIST_ID_MAP.put(GeneListItem.class, "0");
        LIST_ID_MAP.put(MusicArtist.class, "1");
    }

    public static final String LASTFM_API_KEY = "b57d5651-0c90-4aae-a6fe-0ab40b57df58";
    public static final String LASTFM_USER = "erq_";
}
