package com.example.translator.service;

import com.example.translator.entity.Album;
import com.example.translator.entity.request.AlbumRequest;
import com.example.translator.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    public boolean isInputNull(AlbumRequest request) {
        return request.getAlbum_name() == null || request.getAlbum_name().isEmpty() ||
                request.getAlbum_year() == null || request.getAlbum_year().isEmpty();
    }

    public Album findAlbumByName(String album_name){
        return albumRepository.findByName(album_name).orElseThrow(
                () -> new RuntimeException("Album Not Found"));
    }

    public void addAlbum(AlbumRequest request){
        Album album = Album.builder()
                .name(request.getAlbum_name())
                .released_year(request.getAlbum_year())
                .build();
        albumRepository.save(album);
    }

    //TODO: link song to album


}
