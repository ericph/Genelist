package io.github.genelist.util;

import java.util.HashMap;
import java.util.Map;

import io.github.genelist.listitems.*;

public class Constants {
    public static final Map<Class, String> LIST_ID_MAP;
    static {
        LIST_ID_MAP = new HashMap<>();
        LIST_ID_MAP.put(GeneListItem.class, "0");
        LIST_ID_MAP.put(Album.class, "1");
        LIST_ID_MAP.put(Game.class, "2");
        LIST_ID_MAP.put(Musician.class, "3");
    }

    public static final String LASTFM_API_KEY = "c51f49fbbee8dbd1832525f55c8d3190";
    public static final String LASTFM_USER = "erq_";

    public static final String TWITCH_CLIENT_ID = "";
}
