package br.com.kaori.favoritesongs.service;

import br.com.kaori.favoritesongs.dto.SongDto;
import br.com.kaori.favoritesongs.entity.Song;
import br.com.kaori.favoritesongs.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SongServiceImplementation implements SongService {
    @Autowired
    SongRepository songRepository;

    public Song addSong(String nameSong, String nameArtist) {
        Song songToBeAdded = new Song();
        songToBeAdded.setNameSong(nameSong);
        songToBeAdded.setNameArtist(nameArtist);
        return songRepository.save(songToBeAdded);
    }

    public Song updateSong(Long id, String nameSong, String nameArtist) {
        Song songToBeUpdated = songRepository.getReferenceById(id);
        songToBeUpdated.setNameSong(nameSong);
        songToBeUpdated.setNameArtist(nameArtist);
        return songRepository.save(songToBeUpdated);
    }

    public List<SongDto> getAllSongs() {
        List<SongDto> listAllSongs = new ArrayList<>();
        List<Song> listSongs = songRepository.findAll();

        if (!listSongs.isEmpty()) {
            listSongs.forEach(song -> listAllSongs.add(entityToDto(song)));
        }

        return listAllSongs;
    }

    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }

    private SongDto entityToDto(Song songEntity) {
        return new SongDto(songEntity.getNameSong(), songEntity.getNameArtist());
    }
}
