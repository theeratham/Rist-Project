package com.example.translator.service;

import com.example.translator.entity.Artist;
import com.example.translator.entity.request.ArtistRequest;
import com.example.translator.repository.ArtistRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ArtistService {
    @Autowired
    private ArtistRepository artistRepository;

    public boolean isInputNull(ArtistRequest request){
        return request.getArtist_name() == null || request.getArtist_name().isEmpty() ||
                request.getDescription() == null || request.getDescription().isEmpty();
    }

    public List<Artist> findAllArtist(){
        return artistRepository.findAll();
    }

    public Artist findArtistByName(String artist_name){
        return artistRepository.findByName(artist_name).orElseThrow(
                () -> new EntityNotFoundException("Artist Not Found"));
    }

    public void addArtist(ArtistRequest request) throws IOException {
        if (!request.getPic_file().isEmpty()){
            String picPath = "\\Rist-Project\\translator\\translator\\src\\main\\resources\\picture\\" + request.getPic_file().getOriginalFilename();
            Files.write(Paths.get(picPath),request.getPic_file().getBytes());
            Artist artist = Artist.builder()
                    .name(request.getArtist_name())
                    .description(request.getDescription())
                    .picturePath(picPath)
                    .build();
            artistRepository.save(artist);
        } else {
            throw new IOException("File IS Empty");
        }
    }
}
