package com.trylogyed.musicstorerecommendations.repository;

import com.trylogyed.musicstorerecommendations.model.LabelRecommendation;
import com.trylogyed.musicstorerecommendations.model.TrackRecommendation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TrackRecommendationRepositoryTest {

    @Autowired
    TrackRecommendationRepository trackRepository;

    @Before
    public void setUp() throws Exception {
        trackRepository.deleteAll();
    }

    @Test
    public void addGetDeleteTrackRecommendation() {

        TrackRecommendation track = new TrackRecommendation();
        track.setTrackId(1);
        track.setUserId(1);
        track.setLiked(true);

        track = trackRepository.save(track);

        Optional<TrackRecommendation> track1 = trackRepository.findById(track.getTrackRecommendationId());

        assertEquals(track1.get(), track);

        trackRepository.deleteById(track.getTrackRecommendationId());

        track1 = trackRepository.findById(track.getTrackRecommendationId());

        assertFalse(track1.isPresent());
    }

    @Test
    public void updateTrackRecommendation(){
        TrackRecommendation track = new TrackRecommendation();
        track.setTrackId(1);
        track.setUserId(1);
        track.setLiked(true);

        trackRepository.save(track);
        track.setTrackId(1);
        track.setUserId(1);
        track.setLiked(false);

        trackRepository.save(track);

        Optional<TrackRecommendation> track1= trackRepository.findById(track.getTrackRecommendationId());
        assertEquals(track1.get(), track);
    }

    @Test
    public void getAllTrackRecommendation() {

        TrackRecommendation track = new TrackRecommendation();
        track.setTrackId(1);
        track.setUserId(1);
        track.setLiked(true);

        track = trackRepository.save(track);

        TrackRecommendation track1 = new TrackRecommendation();
        track1.setTrackId(2);
        track1.setUserId(2);
        track1.setLiked(false);

        track = trackRepository.save(track1);

        List<TrackRecommendation> tList = trackRepository.findAll();
        assertEquals(tList.size(), 2);

    }

}