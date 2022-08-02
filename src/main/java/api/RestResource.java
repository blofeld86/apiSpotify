package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static api.Route.API;
import static api.Route.TOKEN;
import static api.SpecBuilder.getRequestSpec;
import static api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class RestResource {

    public static Response post(String path, String token, Object requestPlaylist){
        return given(getRequestSpec()).
                                       body(requestPlaylist).
                                       auth().
                                       oauth2(token).
                                       log().
                                       all().
                                when().
                                       post(path).
                                then().
                                       spec(getResponseSpec()).
                                       extract().
                                       response();
    }

    public static Response get(String path, String token){
        return given(getRequestSpec()).
                                       header("Authorization","Bearer "+token).
                                when().
                                       get(path).
                                then().
                                       spec(getResponseSpec()).
                                       extract().
                                       response();
    }

    public static Response update(String path, String token, Object requestPlaylist){
        return given(getRequestSpec()).
                                       body(requestPlaylist).
                                       auth().
                                       oauth2(token).
                                when().
                                       put(path).
                                then().
                                       spec(getResponseSpec()).
                                       extract().
                                       response();
    }

    public static Response addSong(String path, String token, String song, int x){
        return given(getRequestSpec()).
                                       queryParam("position",x).
                                       queryParam("uris", song).
                                       auth().
                                       oauth2(token).
                                when().
                                       post(path).
                                then().
                                       spec(getResponseSpec()).
                                       extract().
                                       response();
    }

    public static Response postAccount(HashMap map){
        return given().
                       contentType(ContentType.URLENC).
                       formParams(map).
                when().
                       post(API + TOKEN).
                then().
                       spec(getResponseSpec()).
                       extract().
                       response();
    }
}
