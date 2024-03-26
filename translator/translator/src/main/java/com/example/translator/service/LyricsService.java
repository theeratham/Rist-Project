package com.example.translator.service;

import com.example.translator.entity.Lyrics;
import com.example.translator.repository.LyricsRepository;
import com.example.translator.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class LyricsService {
    @Autowired
    private LyricsRepository lyricsRepository;

    @Autowired
    private SongRepository songRepository;

    public boolean isInputNull(String song_name) {
        return song_name == null || song_name.isEmpty();
    }

    public void deleteLyrics(Long lyrics_id) {
        lyricsRepository.deleteById(lyrics_id);
    }

    public void addLyrics(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String file_path = "\\Rist-Project\\translator\\translator\\src\\main\\resources\\lyrics\\" + file.getOriginalFilename();
            Files.write(Paths.get(file_path),file.getBytes());
            Lyrics lyrics = Lyrics.builder()
                    .name(file.getOriginalFilename())
                    .filePath(file_path)
                    .build();
            lyricsRepository.save(lyrics);
        } else {
            throw new IOException("File Is Empty");
        }
    }
}
