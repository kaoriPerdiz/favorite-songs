package br.com.kaori.favoritesongs.dto;

import br.com.kaori.favoritesongs.entity.Song;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class SongDtoTest {
    @Test
    @DisplayName("Case 1: testing Song's properties, getters and setters")
    void testingSongPropertiesGetterSetter() {
        SongDto songNoArgs = new SongDto();
        songNoArgs.setNameSong("Back to December");
        songNoArgs.setNameArtist("Taylor Swift");

        SongDto songAllArgs = new SongDto("Back to December", "Taylor Swift");

        Assertions.assertEquals(songAllArgs.getNameSong(), songNoArgs.getNameSong());
        Assertions.assertEquals(songAllArgs.getNameArtist(), songNoArgs.getNameArtist());
    }
}
