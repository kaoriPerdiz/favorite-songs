package br.com.kaori.favoritesongs.service;

import br.com.kaori.favoritesongs.dto.SongDto;
import br.com.kaori.favoritesongs.entity.Song;

import java.util.List;

public interface SongService {
    Song addSong(String nameSong, String nameArtist);

    Song updateSong(Long id, String nameSong, String nameArtist);

    List<SongDto> getAllSongs();

    void deleteSong(Long id);
}
