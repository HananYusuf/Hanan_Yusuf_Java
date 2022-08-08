package com.trylogyed.musicstorerecommendations.controller;

import com.trylogyed.musicstorerecommendations.model.AlbumRecommendation;
import com.trylogyed.musicstorerecommendations.repository.AlbumRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
public class AlbumRecommendationController {

    @Autowired
    AlbumRecommendationRepository albumRecommendationRepositoryRepository;

    @GetMapping("/albumR")
    public List<AlbumRecommendation> getAlbumsR() {
        return albumRecommendationRepositoryRepository.findAll();
    }

    @GetMapping("/albumR/{id}")
    public AlbumRecommendation getAlbumRRById(@PathVariable Integer id) {
        return albumRecommendationRepositoryRepository.findById(id).get();
    }

    @PostMapping("/albumR")
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumRecommendation createAlbumR(@RequestBody AlbumRecommendation album) {
        return albumRecommendationRepositoryRepository.save(album);
    }

    @PutMapping("/albumR")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAlbumR(@RequestBody AlbumRecommendation album) {

        albumRecommendationRepositoryRepository.save(album);
    }

    @DeleteMapping("/albumR/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbumR(@PathVariable Integer id) {
        Optional<AlbumRecommendation> albumToDelete = albumRecommendationRepositoryRepository.findById(id);
        if(albumToDelete.isPresent() == false ) {
            throw new IllegalArgumentException("No Album with the id " + id);
        }
        albumRecommendationRepositoryRepository.deleteById(id);

    }
}
