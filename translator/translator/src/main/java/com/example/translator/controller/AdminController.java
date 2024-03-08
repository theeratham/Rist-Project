package com.example.translator.controller;

import com.example.translator.entity.Song;
import com.example.translator.entity.request.*;
import com.example.translator.entity.response.DataResponse;
import com.example.translator.repository.SongRepository;
import com.example.translator.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private SongService songService;
    @Autowired
    private LyricsService lyricsService;

    @PostMapping("/lyrics/addLyrics")
    public ResponseEntity<DataResponse> addLyrics(@RequestParam("file") MultipartFile file,@RequestParam Long song_id){
        DataResponse response = new DataResponse();
        try {
            lyricsService.uploadLyrics(file, song_id);
            response.setMessage("Lyrics Upload Successfully!!");
            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/lyrics/deleteById/{lyrics_id}")
    public ResponseEntity<DataResponse> deleteLyricsById(@PathVariable Long lyrics_id){
        DataResponse response = new DataResponse();
        if (lyrics_id != null){
            lyricsService.deleteLyrics(lyrics_id);
            response.setMessage("Lyrics Has Been Deleted");
            return ResponseEntity.ok().body(response);
        } else {
            response.setMessage("Lyrics Not Found");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/song/addSong")
    public ResponseEntity<DataResponse> addSong(@RequestParam("file") MultipartFile file){
        DataResponse response = new DataResponse();
        try {
            songService.uploadSong(file);
            response.setMessage("Song uploaded successfully!!");
            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/song/getSongId/{song_id}")
    public ResponseEntity<DataResponse> getSongId(@PathVariable Long song_id){
        DataResponse response = new DataResponse();
        if (song_id != null){
            songService.findSongId(song_id);
            response.setMessage(song_id.toString());
            return ResponseEntity.ok().body(response);
        } else {
            response.setMessage("Song Not Found");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/song/getAllSong")
    public ResponseEntity<DataResponse> getAllSong(){
        DataResponse response = new DataResponse();
        List<Song> songList = songService.findAllSong();
        response.setMessage(songList);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/song/deleteById/{song_id}")
    public ResponseEntity<DataResponse> deleteSongById(@PathVariable Long song_id){
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
