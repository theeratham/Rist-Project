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

    public void uploadLyrics(MultipartFile file) throws IOException {
        if (!file.isEmpty()){
            Lyrics lyrics = Lyrics.builder()
                    .file(file.getBytes())
                    .build();
            lyricsRepository.save(lyrics);
        } else {
            throw new IOException("File Is Empty");
        }
    }

}
