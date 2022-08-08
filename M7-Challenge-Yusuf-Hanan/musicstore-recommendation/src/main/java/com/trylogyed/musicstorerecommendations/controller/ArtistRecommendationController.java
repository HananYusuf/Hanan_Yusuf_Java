package com.trylogyed.musicstorerecommendations.controller;

import com.trylogyed.musicstorerecommendations.model.ArtistRecommendation;
import com.trylogyed.musicstorerecommendations.repository.ArtistRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
public class ArtistRecommendationController {

    @Autowired
    ArtistRecommendationRepository artistRecommendationRepository;

    @GetMapping("/artistR")
    public List<ArtistRecommendation> getArtistR() {
        return artistRecommendationRepository.findAll();
    }

    @GetMapping("/artistR/{id}")
    public ArtistRecommendation getArtistById(@PathVariable int id) {
        return artistRecommendationRepository.findById(id).get();
    }

    @PostMapping("/artistR")
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistRecommendation createArtistR(@RequestBody ArtistRecommendation artist) {
        return artistRecommendationRepository.save(artist);
    }

    @PutMapping("/artistR")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateArtistR(@RequestBody ArtistRecommendation artist) {

        artistRecommendationRepository.save(artist);
    }

    @DeleteMapping("/artistR/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtistR(@PathVariable Integer id) {
        Optional<ArtistRecommendation> artistToDelete = artistRecommendationRepository.findById(id);
        if(artistToDelete.isPresent() == false ) {
            throw new IllegalArgumentException("No Artist with the id " + id);
        }
        artistRecommendationRepository.deleteById(id);

    }
}
