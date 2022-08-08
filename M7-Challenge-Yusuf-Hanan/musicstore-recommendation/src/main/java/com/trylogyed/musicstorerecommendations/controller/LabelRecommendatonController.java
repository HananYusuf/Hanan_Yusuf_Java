package com.trylogyed.musicstorerecommendations.controller;

import com.trylogyed.musicstorerecommendations.model.LabelRecommendation;
import com.trylogyed.musicstorerecommendations.repository.LabelRecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
public class LabelRecommendatonController {

    @Autowired
    LabelRecommendationRepository labelRecommendationRepository;

    @GetMapping("/labelR")
    public List<LabelRecommendation> getLabelR() {
        return labelRecommendationRepository.findAll();
    }

    @GetMapping("/labelR/{id}")
    public LabelRecommendation getLabelById(@PathVariable int id) {
        return labelRecommendationRepository.findById(id).get();
    }

    @PostMapping("/labelR")
    @ResponseStatus(HttpStatus.CREATED)
    public LabelRecommendation createLabelR(@RequestBody LabelRecommendation label) {
        return labelRecommendationRepository.save(label);
    }

    @PutMapping("/labelR")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLabelR(@RequestBody LabelRecommendation label) {

        labelRecommendationRepository.save(label);
    }

    @DeleteMapping("/labelR/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabel(@PathVariable Integer id) {
        Optional<LabelRecommendation> labelToDelete = labelRecommendationRepository.findById(id);
        if(labelToDelete.isPresent() == false ) {
            throw new IllegalArgumentException("No Label with the id " + id);
        }
        labelRecommendationRepository.deleteById(id);
    }
}
