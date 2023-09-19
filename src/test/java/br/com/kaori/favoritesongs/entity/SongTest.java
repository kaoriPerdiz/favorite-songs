package br.com.kaori.favoritesongs.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class SongTest {
    @Test
    @DisplayName("Case 1: testing Song's properties, getters and setters")
    void testingSongPropertiesGetterSetter() {
        Song songNoArgs = new Song();
        songNoArgs.setId(1L);
        songNoArgs.setNameSong("Love Story");
        songNoArgs.setNameArtist("Taylor Swift");

        Song songAllArgs = new Song(1L, "Love Story", "Taylor Swift");

        Assertions.assertEquals(songAllArgs.getId(), songNoArgs.getId());
        Assertions.assertEquals(songAllArgs.getNameSong(), songNoArgs.getNameSong());
        Assertions.assertEquals(songAllArgs.getNameArtist(), songNoArgs.getNameArtist());
    }
}
