package com.example.translator.controller;

import com.example.translator.entity.Playlist;
import com.example.translator.entity.request.PlaylistRequest;
import com.example.translator.entity.response.DataResponse;
import com.example.translator.repository.PlaylistRepository;
import com.example.translator.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;
    @Autowired
    private PlaylistRepository playlistRepository;

    @GetMapping("/findByName")
    public ResponseEntity<DataResponse> findByName(@RequestParam String playlist_name){
        DataResponse response = new DataResponse();
        try {
            response.setMessage(playlistService.findPlaylistByName(playlist_name));
            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<DataResponse> findAll(){
        DataResponse response = new DataResponse();
        List<Playlist> playlists = playlistService.findAllPlaylist();
        response.setMessage(playlists);
        return ResponseEntity.ok().body(response);
    }
    @PostMapping("/addPlaylist")
    public ResponseEntity<DataResponse> addPlaylist(@RequestBody PlaylistRequest request){
        DataResponse response = new DataResponse();
        if (!playlistService.isInputNull(request)){
            if (playlistRepository.existsByName(request.getPlaylist_name())){
                response.setMessage("Playlist Name Already Existed");
                return ResponseEntity.badRequest().body(response);
            } else {
                try {
                    playlistService.addPlaylist(request);
                    response.setMessage("Playlist Added Successfully!!");
                    return ResponseEntity.ok().body(response);
                } catch (Exception e) {
                    response.setMessage(e.getMessage());
                    return ResponseEntity.badRequest().body(response);
                }
            }
        }
        response.setMessage("Field Is Empty");
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/addSongToPlaylist")
    public ResponseEntity<DataResponse> addSongToPlaylist(@RequestParam Long playlist_id, @RequestParam Long song_id){
        DataResponse response = new DataResponse();
        try {
            playlistService.addSongToPlaylist(playlist_id, song_id);
            response.setMessage("Song Has Been Added To Playlist");
            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/editPlaylist")
    public ResponseEntity<DataResponse> editPlaylist(@RequestParam Long playlist_id,@RequestParam String playlist_name){
        DataResponse response = new DataResponse();
        if (playlist_name != null) {
            try {
                playlistService.editPlaylist(playlist_id, playlist_name);
                response.setMessage("Playlist Edited");
                return ResponseEntity.ok().body(response);
            } catch (Exception e) {
                response.setMessage(e.getMessage());
                return ResponseEntity.badRequest().body(response);
            }
        } else {
            response.setMessage("Input Field Cannot Be Empty");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/deletePlaylist/{playlist_id}")
    public ResponseEntity<DataResponse> deletePlaylist(@PathVariable Long playlist_id){
        DataResponse response = new DataResponse();
        if (playlist_id != null){
            playlistService.deletePlaylist(playlist_id);
            response.setMessage("Playlist Has Been Deleted");
            return ResponseEntity.ok().body(response);
        } else {
            response.setMessage("Playlist Not Found");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
