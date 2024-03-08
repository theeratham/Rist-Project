package com.example.translator.service;

import com.example.translator.entity.Song;
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

    public void findSongId(Long song_id){
        songRepository.findById(song_id);
    }

    public List<Song> findAllSong(){
        return songRepository.findAll();
    }

    public void deleteSong(Long song_id){
        songRepository.deleteById(song_id);
    }
    public void uploadSong(MultipartFile file) throws IOException {
        if (!file.isEmpty()){
            Song song = Song.builder()
                    .name(file.getOriginalFilename())
                    .file(file.getBytes())
                    .build();
            songRepository.save(song);
        } else {
            throw new RuntimeException("File Is Empty");
        }
    }
}
