package com.trylogyed.musicstorerecommendations.controller;

import com.trylogyed.musicstorerecommendations.model.TrackRecommendation;
import com.trylogyed.musicstorerecommendations.repository.TrackRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
public class TrackRecommendationController {

    @Autowired
    TrackRecommendationRepository trackRecommendationRepository;

    @GetMapping("/trackR")
    public List<TrackRecommendation> getTrackR() {
        return trackRecommendationRepository.findAll();
    }

    @GetMapping("/trackR/{id}")
    public TrackRecommendation getTrackId(@PathVariable Integer id) {
        return trackRecommendationRepository.findById(id).get();
    }

    @PostMapping("/trackR")
    @ResponseStatus(HttpStatus.CREATED)
    public TrackRecommendation createTrackR(@RequestBody TrackRecommendation track) {
       return trackRecommendationRepository.save(track);
    }

    @PutMapping("/trackR")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTrackR(@RequestBody TrackRecommendation track) {

        trackRecommendationRepository.save(track);
    }

    @DeleteMapping("/trackR/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletetrackR(@PathVariable Integer id) {
        Optional<TrackRecommendation> trackToDelete = trackRecommendationRepository.findById(id);
        if(trackToDelete.isPresent() == false ) {
            throw new IllegalArgumentException("No Track with the id " + id);
        }
        trackRecommendationRepository.deleteById(id);

    }
}
