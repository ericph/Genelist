package io.github.genelist.util;

import java.util.HashMap;
import java.util.Map;

import io.github.genelist.listitems.*;

public class Constants {
    public static final Map<Class, Long> LIST_ID_MAP;
    static {
        LIST_ID_MAP = new HashMap<>();
        LIST_ID_MAP.put(GeneListItem.class, (long) 0);
        LIST_ID_MAP.put(MusicArtist.class, (long) 1);
    }

    public static final String LASTFM_API_KEY = "b57d5651-0c90-4aae-a6fe-0ab40b57df58";
    public static final String LASTFM_SHARED_SECRET = "c51f49fbbee8dbd1832525f55c8d3190";
    public static final String LASTFM_USER = "erq_";
}
