package com.trylogyed.musicstorerecommendations.repository;
import com.trylogyed.musicstorerecommendations.model.ArtistRecommendation;
import com.trylogyed.musicstorerecommendations.model.TrackRecommendation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ArtistRecommendationRepositoryTest {

    @Autowired
    ArtistRecommendationRepository artistRepository;

    @Before
    public void setUp() throws Exception {
        artistRepository.deleteAll();
    }

    @Test
    public void addGetDeleteArtistRecommendation() {

        ArtistRecommendation artist = new ArtistRecommendation();
        artist.setArtistId(1);
        artist.setUserId(1);
        artist.setLiked(true);

        artist = artistRepository.save(artist);

        Optional<ArtistRecommendation> artist1 = artistRepository.findById(artist.getArtistRecommendationId());

        assertEquals(artist1.get(), artist);

        artistRepository.deleteById(artist.getArtistRecommendationId());

        artist1 = artistRepository.findById(artist.getArtistRecommendationId());

        assertFalse(artist1.isPresent());
    }

    @Test
    public void updateArtistRecommendation() {

        ArtistRecommendation artist = new ArtistRecommendation();
        artist.setArtistRecommendationId(1);
        artist.setArtistId(1);
        artist.setUserId(1);
        artist.setLiked(true);

        artist = artistRepository.save(artist);

        artist.setArtistRecommendationId(1);
        artist.setArtistId(1);
        artist.setUserId(1);
        artist.setLiked(false);

        artist = artistRepository.save(artist);

        Optional<ArtistRecommendation> artist1 = artistRepository.findById(artist.getArtistRecommendationId());
        assertEquals(artist1.get(), artist);
    }

    @Test
    public void getAllArtistRecommendations() {

        ArtistRecommendation artist1 = new ArtistRecommendation();
        artist1.setArtistRecommendationId(1);
        artist1.setArtistId(1);
        artist1.setUserId(1);
        artist1.setLiked(true);

        artistRepository.save(artist1);

        ArtistRecommendation artist2 = new ArtistRecommendation();
        artist2.setArtistRecommendationId(2);
        artist2.setArtistId(2);
        artist2.setUserId(2);
        artist2.setLiked(false);

        artistRepository.save(artist2);

        List<ArtistRecommendation> aList = artistRepository.findAll();
        assertEquals(aList.size(), 2);

    }


}