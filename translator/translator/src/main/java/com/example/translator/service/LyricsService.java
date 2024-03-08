package com.example.translator.service;

import com.example.translator.entity.Lyrics;
import com.example.translator.entity.Song;
import com.example.translator.repository.LyricsRepository;
import com.example.translator.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class LyricsService {
    @Autowired
    private LyricsRepository lyricsRepository;

    @Autowired
    private SongRepository songRepository;

    public void deleteLyrics(Long lyrics_id){
        lyricsRepository.deleteById(lyrics_id);
    }

    public void uploadLyrics(MultipartFile file, Long song_id) throws IOException {
        Song song = songRepository.findById(song_id).orElseThrow(() -> new RuntimeException("Song Not Found"));
        if (!file.isEmpty()){
            Lyrics lyrics = Lyrics.builder()
                    .file(file.getBytes())
                    .song(song)
                    .build();
        } else {
            throw new RuntimeException("File Is Empty");
        }
    }
}
