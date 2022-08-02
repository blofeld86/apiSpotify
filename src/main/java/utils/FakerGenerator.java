package utils;

import com.github.javafaker.Faker;

public class FakerGenerator {

    private static Faker faker = new Faker();
    public static String getPlaylistName(){return "Playlist "+faker.name().title();}
    public static String getPlaylistDescription(){return "Description "+faker.rockBand().name();}
}
