package io.github.genelist.listitems;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import io.github.genelist.base.ImageItem;
import io.github.genelist.base.ListItem;
import io.github.genelist.util.Constants;
import io.github.genelist.util.Http;

public class Game extends ListItem {
    private static final Logger LOG = Logger.getLogger("io.github.genelist.listitems.Game");

    private String id = ListItem.DEFAULT_ID;
    private String name = "";
    private ImageItem image = new ImageItem();

    public Game() { super(); }

    public Game(String id, String name, ImageItem image) {
        exists = true;
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public static Game getById(String id) {
        String url = "https://api.twitch.tv/helix/games?id=" + id;
        return fromJSON(Http.getRequest(url, "Client-ID", Constants.TWITCH_CLIENT_ID));
    }

    public static Game getByName(String name) {
        String url = "https://api.twitch.tv/helix/games?name=" + name;
        return fromJSON(Http.getRequest(url, "Client-ID", Constants.TWITCH_CLIENT_ID));
    }

    private static Game fromJSON(String json) {
        ObjectMapper mapper = new ObjectMapper();
        Game game = new Game();
        try {
            String data = mapper.readValue(json, Map.class).get("data").toString();
            if (data.charAt(0) == '[')
                data = data.substring(1, data.length() - 1);
            Map m = mapper.readValue(data, Map.class);
            String gameId = m.get("id").toString();
            String gameName = m.get("name").toString();
            String imageUrl = m.get("box_art_url").toString();
            game = new Game(gameId, gameName, new ImageItem(imageUrl));
        } catch (IOException e) {
            LOG.warning(e.getMessage());
        }
        return game;
    }

    @Override
    public String getId() { return id; }

    @Override
    public long getLongId() { return Long.parseLong(id); }

    public String getName() { return name; }

    @Override
    public ImageItem getImage() { return image; }
}
