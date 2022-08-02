import api.PlaylistAPI;
import data.DataStore;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import model.playlist.Playlist;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.post;
import static java.lang.System.getProperty;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.FakerGenerator.getPlaylistDescription;
import static utils.FakerGenerator.getPlaylistName;

@Epic("Spotify Oauth 2.0")
@Feature("Playlist API")
public class PlaylistTest extends BaseTest{

    @Step("Playlist Builder")
    private Playlist playlistBuilder(String name, String description, boolean _public){
        return Playlist.builder().name(name).description(description)._public(_public).build();
    }

    @Step("Assert Playlist Equal")
    private void assertPlaylistEqual(Playlist response, Playlist request){
        assertThat(response.getName()).isEqualTo(request.getName());
        assertThat(response.getDescription()).isEqualTo(request.getDescription());
        assertThat(response.get_public()).isEqualTo(request.get_public());
    }

    @Test(priority = 1)
    public void createPlaylistTest() throws InterruptedException {
        Playlist playlist = playlistBuilder(getPlaylistName(),getPlaylistDescription(),true);
        Response response = PlaylistAPI.post(playlist);
        Playlist playResp = response.as(Playlist.class);
        DataStore.PLAYLIST_ID = response.path("id");
        Thread.sleep(3000);
        assertPlaylistEqual(playResp,playlist);
        System.out.println(DataStore.PLAYLIST_ID);
    }

    @Test(priority = 2)
    public void addSongsTest(){
        Response response = PlaylistAPI.addSong(DataStore.PLAYLIST_ID,getProperty("blur"),0);
        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_CREATED);
        PlaylistAPI.addSong(DataStore.PLAYLIST_ID, getProperty("immortality"),1);
        PlaylistAPI.addSong(DataStore.PLAYLIST_ID, getProperty("oczy"),2);
    }




}
