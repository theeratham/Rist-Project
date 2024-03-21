package com.example.translator.controller;

import com.example.translator.entity.response.DataResponse;
import com.example.translator.service.LyricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("/lyrics")
public class LyricsController {
    @Autowired
    private LyricsService lyricsService;
    @PostMapping("/addLyrics")
    public ResponseEntity<DataResponse> addLyrics(@RequestParam("file") MultipartFile file){
        DataResponse response = new DataResponse();
        try {
            lyricsService.uploadLyrics(file);
            response.setMessage("Lyrics Upload Successfully!!");
            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/deleteLyrics")
    public ResponseEntity<DataResponse> deleteLyrics(@RequestParam Long lyrics_id){
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
}
