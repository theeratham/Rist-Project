package com.example.translator.controller;

import com.example.translator.entity.Song;
import com.example.translator.entity.request.PlaylistRequest;
import com.example.translator.entity.request.UserRequest;
import com.example.translator.entity.response.DataResponse;
import com.example.translator.repository.PlaylistRepository;
import com.example.translator.repository.UserRepository;
import com.example.translator.service.PlaylistService;
import com.example.translator.service.SongService;
import com.example.translator.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PlaylistService playlistService;
    @Autowired
    private PlaylistRepository playlistRepository;

    @PostMapping("/playlist/addPlaylist")
    public ResponseEntity<DataResponse> createPlaylist(@RequestBody PlaylistRequest request){
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

    @PutMapping("/playlist/renamePlaylist/{playlist_id}")
    public ResponseEntity<DataResponse> editPlaylist(@PathVariable Long playlist_id, String playlist_name){
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
            response.setMessage("Input Field Cannot Be Null Or Empty");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/playlist/deleteById/{playlist_id}")
    public ResponseEntity<DataResponse> deletePlaylistById(@PathVariable Long playlist_id){
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

    @PostMapping("/playlist/addSongToPlaylist")
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
}
