package com.example.translator.service;

import com.example.translator.entity.Album;
import com.example.translator.entity.Artist;
import com.example.translator.entity.Lyrics;
import com.example.translator.entity.Song;
import com.example.translator.entity.request.SongRequest;
import com.example.translator.repository.AlbumRepository;
import com.example.translator.repository.ArtistRepository;
import com.example.translator.repository.LyricsRepository;
import com.example.translator.repository.SongRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private ArtistRepository artistRepository;
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
        if (!request.getPic_file().isEmpty() && !request.getSong_file().isEmpty()){
            String songPath = "\\Rist-Project\\translator\\translator\\src\\main\\resources\\audio\\" + request.getSong_file().getOriginalFilename();
            String picPath = "\\Rist-Project\\translator\\translator\\src\\main\\resources\\picture\\" + request.getPic_file().getOriginalFilename();
            Files.write(Paths.get(songPath),request.getSong_file().getBytes());
            Files.write(Paths.get(picPath),request.getPic_file().getBytes());
            Album album = albumRepository.findById(request.getAlbum_id()).orElseThrow(() -> new EntityNotFoundException("Album Not Found"));
            Artist artist = artistRepository.findById(request.getArtist_id()).orElseThrow(() -> new EntityNotFoundException("Artist Not Found"));
            Lyrics lyrics = lyricsRepository.findById(request.getLyrics_id()).orElseThrow(() -> new EntityNotFoundException("Lyrics Not Found"));
            Song song = Song.builder()
                    .name(request.getSong_file().getOriginalFilename())
                    .album(album)
                    .artist(artist)
                    .lyrics(lyrics)
                    .filePath(songPath)
                    .picturePath(picPath)
                    .build();
            songRepository.save(song);
        } else {
            throw new IOException("File Is Empty");
        }
    }

    public List<Object> search(String input){
        List<Object> result = new ArrayList<>();
        List<Artist> artists = artistRepository.findByNameContaining(input);
        List<Album> albums = albumRepository.findByNameContaining(input);
        List<Song> songs = songRepository.findByNameContaining(input);
        result.add(artists);
        result.add(albums);
        result.add(songs);
        return result;
    }
}
