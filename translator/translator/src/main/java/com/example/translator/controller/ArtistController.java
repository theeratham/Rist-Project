package com.example.translator.controller;

import com.example.translator.entity.Artist;
import com.example.translator.entity.request.ArtistRequest;
import com.example.translator.entity.response.DataResponse;
import com.example.translator.repository.ArtistRepository;
import com.example.translator.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistController {
    @Autowired
    private ArtistService artistService;
    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping("/findByName")
    public ResponseEntity<DataResponse> findByName(@RequestParam String artist_name){
        DataResponse response = new DataResponse();
        try {
            response.setMessage(artistService.findArtistByName(artist_name));
            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<DataResponse> findAll(){
        DataResponse response = new DataResponse();
        List<Artist> artists = artistService.findAllArtist();
        response.setMessage(artists);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/addArtist")
    public ResponseEntity<DataResponse> addArtist(@RequestBody ArtistRequest request){
        DataResponse response = new DataResponse();
        if (!artistService.isInputNull(request)){
            if (artistRepository.existsByName(request.getArtist_name())){
                response.setMessage("Artist Name Already Existed");
                return ResponseEntity.badRequest().body(response);
            } else {
                try {
                    artistService.addArtist(request);
                    response.setMessage("Artist Added Successfully!!");
                    return ResponseEntity.ok().body(response);
                } catch (Exception e){
                    response.setMessage(e.getMessage());
                    return ResponseEntity.badRequest().body(response);
                }
            }
        }
        response.setMessage("Input Field Cannot Be Empty");
        return ResponseEntity.badRequest().body(response);
    }
}
