package br.com.kaori.favoritesongs.controller;

import br.com.kaori.favoritesongs.dto.SongDto;
import br.com.kaori.favoritesongs.entity.Song;
import br.com.kaori.favoritesongs.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
public class SongController {
    @Autowired
    private SongService songService;

    @RequestMapping(value = "/song/", method = RequestMethod.POST)
    public ResponseEntity<Song> addSong(
            @RequestParam String nameSong,
            @RequestParam String nameArtist) {
        Song addedSong = songService.addSong(nameSong, nameArtist);

        return ResponseEntity.ok(addedSong);
    }

    @RequestMapping(value = "/song/", method = RequestMethod.GET)
    public ResponseEntity<List<SongDto>> getAllSongs() {
        List<SongDto> allSongs = songService.getAllSongs();

        return ResponseEntity.ok(allSongs);
    }

    @RequestMapping(value = "/song/", method = RequestMethod.PUT)
    public ResponseEntity<Song> updateSong(
            @RequestParam Long id,
            @RequestParam String nameSong,
            @RequestParam String nameArtist) {
        Song updatedSong = songService.updateSong(id, nameSong, nameArtist);

        return ResponseEntity.ok(updatedSong);
    }

    @RequestMapping(value = "/song/", method = RequestMethod.DELETE)
    public ResponseEntity deleteSong(@RequestParam Long id) {
        songService.deleteSong(id);

        return ResponseEntity.ok(new ResponseEntity<>(HttpStatusCode.valueOf(200)));
    }
}
