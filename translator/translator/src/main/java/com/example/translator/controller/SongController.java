package com.example.translator.controller;

import com.example.translator.entity.Song;
import com.example.translator.entity.response.DataResponse;
import com.example.translator.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/song")
public class SongController {
    @Autowired
    private SongService songService;

    @GetMapping("/findAll")
    public ResponseEntity<DataResponse> findAll(){
        DataResponse response = new DataResponse();
        List<Song> songList = songService.findAllSong();
        response.setMessage(songList);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/findId")
    public ResponseEntity<DataResponse> findId(@RequestParam Long song_id){
        DataResponse response = new DataResponse();
        try {
            response.setMessage(songService.findSongById(song_id));
            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/findByName")
    public ResponseEntity<DataResponse> findByName(@RequestParam String song_name){
        DataResponse response = new DataResponse();
        try {
            response.setMessage(songService.findSongByName(song_name));
            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/addSong")
    public ResponseEntity<DataResponse> addSong(@RequestParam("file") MultipartFile file, @RequestParam Long album_id, @RequestParam Long lyrics_id){
        DataResponse response = new DataResponse();
        try {
            songService.uploadSong(file,album_id,lyrics_id);
            response.setMessage("Song uploaded successfully!!");
            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/deleteSong")
    public ResponseEntity<DataResponse> deleteSong(@RequestParam Long song_id){
        DataResponse response = new DataResponse();
        if (song_id != null){
            songService.deleteSong(song_id);
            response.setMessage("Song Has Been Deleted");
            return ResponseEntity.ok().body(response);
        } else {
            response.setMessage("Song Not Found");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
