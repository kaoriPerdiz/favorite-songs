package br.com.kaori.favoritesongs.service;

import br.com.kaori.favoritesongs.dto.SongDto;
import br.com.kaori.favoritesongs.entity.Song;
import br.com.kaori.favoritesongs.repository.SongRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SongServiceTest {
    @Mock
    private SongRepository songRepository;

    @InjectMocks
    private SongServiceImplementation songService;

    @Test
    @DisplayName("Case 1: success by adding a song")
    void successAddingSong() {
        Long id = 1L;
        Song expectedResult = new Song();
        expectedResult.setId(id);
        expectedResult.setNameSong("Enchanted");
        expectedResult.setNameArtist("Taylor Swift");

        Song songParams = new Song();
        songParams.setNameSong("Enchanted");
        songParams.setNameArtist("Taylor Swift");

        when(songRepository.save(songParams)).thenReturn(expectedResult);

        Song actualResult = songService.addSong("Enchanted", "Taylor Swift");
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Case 2: success by updating a song")
    void successUpdatingSong() {
        Long id = 1L;

        Song expectedResult = new Song();
        expectedResult.setId(id);
        expectedResult.setNameSong("Enchanted");
        expectedResult.setNameArtist("Taylor Swift");

        Song songParams = new Song();
        songParams.setId(id);
        songParams.setNameSong("Lover");
        songParams.setNameArtist("Taylor Swift");

        Song expectedResultSave = new Song();
        expectedResultSave.setId(id);
        expectedResultSave.setNameSong("Lover");
        expectedResultSave.setNameArtist("Taylor Swift");

        when(songRepository.getReferenceById(id)).thenReturn(expectedResult);
        when(songRepository.save(songParams)).thenReturn(expectedResultSave);

        Song actualResult = songService.updateSong(id, "Lover", "Taylor Swift");
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Case 3: success by getting all the songs")
    void successGettingSongsList() {
        Long id = 1L;
        List<SongDto> listAllSongs = new ArrayList<>();
        List<Song> listSongs = new ArrayList<>(); // songRepository.findAll();

        SongDto songDto = new SongDto("You Belong with Me", "Taylor Swift");
        Song songEntity = new Song(id, "You Belong with Me", "Taylor Swift");

        listAllSongs.add(songDto);
        listSongs.add(songEntity);

        when(songRepository.findAll()).thenReturn(listSongs);

        List<SongDto> allSongs = songService.getAllSongs();

        Assertions.assertEquals(listAllSongs, allSongs);
    }

    @Test
    @DisplayName("Case 4: success by deleting a song")
    void successDeletingSongsList() {
        Long id = 1L;
        songService.deleteSong(id);

        verify(songRepository).deleteById(id);
    }
}
