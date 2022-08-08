package com.trylogyed.musicstorecatalog.controller;

import com.trylogyed.musicstorecatalog.model.Album;
import com.trylogyed.musicstorecatalog.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AlbumController {

    @Autowired
    AlbumRepository albumRepository;

    @GetMapping("/album")
    public List<Album> getAlbums() {
        return albumRepository.findAll();
    }

    @GetMapping("/album/{id}")
    public Album getAlbumById(@PathVariable Integer id) {
        return albumRepository.findById(id).get();
    }

    @PostMapping("/album")
    @ResponseStatus(HttpStatus.CREATED)
    public Album createAlbum(@RequestBody Album album) {
       return albumRepository.save(album);
    }

    @PutMapping("/album")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAlbum(@RequestBody Album album) {

        albumRepository.save(album);
    }

    @DeleteMapping("/album/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable Integer id) {
        Optional<Album> albumToDelete = albumRepository.findById(id);
        if(albumToDelete.isPresent() == false ) {
            throw new IllegalArgumentException("No Album with the id " + id);
        }
        albumRepository.deleteById(id);

    }
}
