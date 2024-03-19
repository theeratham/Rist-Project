package com.example.translator.service;

import com.example.translator.entity.Artist;
import com.example.translator.entity.request.ArtistRequest;
import com.example.translator.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistService {
    @Autowired
    private ArtistRepository artistRepository;

    public boolean isInputNull(ArtistRequest request){
        return request.getArtist_name() == null || request.getArtist_name().isEmpty() ||
                request.getArtist_description() == null || request.getArtist_description().isEmpty();
    }

    public Artist findArtistByName(String artist_name){
        return artistRepository.findByName(artist_name).orElseThrow(
                () -> new RuntimeException("Artist Not Found"));
    }

    public void addArtist(ArtistRequest request){
        Artist artist = Artist.builder()
                .name(request.getArtist_name())
                .description(request.getArtist_description())
                .build();
        artistRepository.save(artist);
    }
}
