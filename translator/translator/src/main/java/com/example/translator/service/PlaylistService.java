package com.example.translator.service;

import com.example.translator.entity.Playlist;
import com.example.translator.entity.Song;
import com.example.translator.entity.UserInfo;
import com.example.translator.entity.request.PlaylistRequest;
import com.example.translator.repository.PlaylistRepository;
import com.example.translator.repository.SongRepository;
import com.example.translator.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private SongRepository songRepository;

    public void deletePlaylist(Long playlist_id){
        playlistRepository.deleteById(playlist_id);
    }

    public Playlist findPlaylistByName(String playlist_name){
        return playlistRepository.findByName(playlist_name).orElseThrow(
                () -> new EntityNotFoundException("Playlist Not Found"));
    }

    public List<Playlist> findAllPlaylist(){
        return playlistRepository.findAll();
    }

    public boolean isInputNull(PlaylistRequest request){
        return request.getPlaylist_name() == null || request.getPlaylist_name().isEmpty() ||
                request.getUser_id() == null;
    }

    public void addPlaylist(PlaylistRequest request){
        UserInfo user = userRepository.findById(request.getUser_id()).orElseThrow(() -> new EntityNotFoundException("User Not Found"));
        Playlist playlist = Playlist.builder()
                .name(request.getPlaylist_name())
                .user(user)
                .build();
        playlistRepository.save(playlist);
    }

    public void editPlaylist(Long playlist_id, String playlist_name){
        Playlist playlist = playlistRepository.findById(playlist_id).orElseThrow(() -> new EntityNotFoundException("Playlist Not Found"));
        playlist.setName(playlist_name);
        playlistRepository.save(playlist);
    }
    public void addSongToPlaylist(Long playlist_id, Long song_id){
        Playlist playlist = playlistRepository.findById(playlist_id).orElseThrow(() -> new EntityNotFoundException("Playlist Not Found"));
        Song song = songRepository.findById(song_id).orElseThrow(() -> new EntityNotFoundException("Song Not Found"));
        if (!playlist.getSongs().contains(song)){
            playlist.getSongs().add(song);
            playlistRepository.save(playlist);
        } else {
            throw new RuntimeException("Song Already Exist For This Playlist");
        }
    }
}
