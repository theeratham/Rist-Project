package com.example.translator.controller;

import com.example.translator.entity.Album;
import com.example.translator.entity.request.AlbumRequest;
import com.example.translator.entity.response.DataResponse;
import com.example.translator.repository.AlbumRepository;
import com.example.translator.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private AlbumRepository albumRepository;

    @GetMapping("/findByName")
    public ResponseEntity<DataResponse> findByName(@RequestParam String album_name){
        DataResponse response = new DataResponse();
        try {
            response.setMessage(albumService.findAlbumByName(album_name));
            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            response.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<DataResponse> findAll(){
        DataResponse response = new DataResponse();
        List<Album> albums = albumService.findAllAlbum();
        response.setMessage(albums);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/addAlbum")
    public ResponseEntity<DataResponse> addAlbum(@RequestBody AlbumRequest request){
        DataResponse response = new DataResponse();
        if (!albumService.isInputNull(request)){
            if (albumRepository.existsByName(request.getAlbum_name())){
                response.setMessage("Album Name Already Existed");
                return ResponseEntity.badRequest().body(response);
            } else {
                try {
                    albumService.addAlbum(request);
                    response.setMessage("Album Added Successfully!!");
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
