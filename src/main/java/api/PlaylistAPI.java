package api;
import api.RestResource;
import io.restassured.response.Response;
import model.playlist.Playlist;

import static api.Route.*;
import static api.TokenManager.getToken;
import static java.lang.System.getProperty;

public class PlaylistAPI {

    public static Response post(Playlist playlist){
        return RestResource.post(USERS + getProperty("userID") + PLAYLISTS, getToken(), playlist);
    }

    public static Response post(String token,Playlist playlist){
        return RestResource.post(token,USERS + getProperty("userID") + PLAYLISTS, playlist);
    }

    public static Response get(String playlistID){
        return RestResource.get(PLAYLISTS + "/"+ playlistID, getToken());
    }

    public static Response update(String playlistID, Playlist playlist){
        return RestResource.update(PLAYLISTS+"/"+playlistID,getToken(),playlist);
    }

    public static Response addSong(String playlistID, String song, int position){
        return RestResource.addSong(PLAYLISTS + "/" + playlistID + TRACKS, getToken(), song, position);
    }
}
