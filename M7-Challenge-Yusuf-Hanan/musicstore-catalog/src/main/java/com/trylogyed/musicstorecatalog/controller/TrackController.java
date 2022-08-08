package com.trylogyed.musicstorecatalog.controller;
import com.trylogyed.musicstorecatalog.model.Track;
import com.trylogyed.musicstorecatalog.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TrackController {

    @Autowired
    TrackRepository trackRepository;

    @GetMapping("/track")
    public List<Track> getTrack() {
        return trackRepository.findAll();
    }

    @GetMapping("/track/{id}")
    public Track getTrackId(@PathVariable Integer id) {
        return trackRepository.findById(id).get();
    }

    @PostMapping("/track")
    @ResponseStatus(HttpStatus.CREATED)
    public Track createTrack(@RequestBody Track track) {
       return trackRepository.save(track);
    }

    @PutMapping("/track")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTrack(@RequestBody Track track) {

        trackRepository.save(track);
    }

    @DeleteMapping("/track/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrack(@PathVariable Integer id) {
        Optional<Track> trackToDelete = trackRepository.findById(id);
        if(trackToDelete.isPresent() == false ) {
            throw new IllegalArgumentException("No Track with the id " + id);
        }
        trackRepository.deleteById(id);

    }
}
