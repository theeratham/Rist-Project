package com.example.translator.service;

import com.example.translator.entity.Album;
import com.example.translator.entity.Lyrics;
import com.example.translator.entity.Song;
import com.example.translator.entity.request.SongRequest;
import com.example.translator.repository.AlbumRepository;
import com.example.translator.repository.LyricsRepository;
import com.example.translator.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private LyricsRepository lyricsRepository;

    public Song findSongById(Long song_id){
        return songRepository.findById(song_id).orElseThrow(
                () -> new RuntimeException("Song Not Found"));
    }

    public Song findSongByName(String song_name){
        return songRepository.findByName(song_name).orElseThrow(
                () -> new RuntimeException("Song Not Found"));
    }

    public List<Song> findAllSong(){
        return songRepository.findAll();
    }

    public void deleteSong(Long song_id){
        songRepository.deleteById(song_id);
    }

    public void addSong(SongRequest request) throws IOException {
        if (!request.getFile().isEmpty()){
            Album album = albumRepository.findById(request.getAlbum_id()).orElseThrow(() -> new RuntimeException("Album Not Found"));
            Lyrics lyrics = lyricsRepository.findById(request.getLyrics_id()).orElseThrow(() -> new RuntimeException("Lyrics Not Found"));
            Song song = Song.builder()
                    .name(request.getFile().getOriginalFilename())
                    .file(request.getFile().getBytes())
                    .album(album)
                    .lyrics(lyrics)
                    .build();
            songRepository.save(song);
        } else {
            throw new IOException("File Is Empty");
        }
    }
}
