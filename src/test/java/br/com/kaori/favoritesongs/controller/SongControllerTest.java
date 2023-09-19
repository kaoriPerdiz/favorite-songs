package br.com.kaori.favoritesongs.controller;

import br.com.kaori.favoritesongs.dto.SongDto;
import br.com.kaori.favoritesongs.entity.Song;
import br.com.kaori.favoritesongs.service.SongService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(SongController.class)
public class SongControllerTest {
    @MockBean
    private SongService songService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        when(songService.addSong("Nightmare", "Avenged Sevenfold"))
                .thenReturn(new Song(1L, "Nightmare", "Avenged Sevenfold"));

        when(songService.updateSong(2L, "Afterlife", "Avenged Sevenfold"))
                .thenReturn(new Song(2L, "Afterlife", "Avenged Sevenfold"));

        List<SongDto> mockSongs = new ArrayList<>();
        mockSongs.add(new SongDto("Bat Country", "Avenged Sevenfold"));
        when(songService.getAllSongs()).thenReturn(mockSongs);
    }

    @Test
    @DisplayName("Case 1: success by adding a song")
    void successAddSong() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/song/")
                        .param("nameSong", "Nightmare")
                        .param("nameArtist", "Avenged Sevenfold")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nameSong").value("Nightmare"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nameArtist").value("Avenged Sevenfold"));
    }

    @Test
    @DisplayName("Case 2: success by getting all the songs")
    void successGettingAllSongs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/song/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nameSong").value("Bat Country"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nameArtist").value("Avenged Sevenfold"));
    }

    @Test
    @DisplayName("Case 3: success by updating a song")
    void successUpdateSong() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/song/")
                        .param("id", "2")
                        .param("nameSong", "Afterlife")
                        .param("nameArtist", "Avenged Sevenfold")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nameSong").value("Afterlife"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nameArtist").value("Avenged Sevenfold"));
    }

    @Test
    @DisplayName("Case 4: success by deleting a song")
    void successDeleteSong() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/song/")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
