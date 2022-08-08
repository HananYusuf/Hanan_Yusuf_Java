package com.trylogyed.musicstorerecommendations.repository;
import com.trylogyed.musicstorerecommendations.model.AlbumRecommendation;
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
public class AlbumRecommendationRepositoryTest {

    @Autowired
    AlbumRecommendationRepository albumRepository;

    @Before
    public void setUp() throws Exception {
        albumRepository.deleteAll();
    }

    @Test
    public void addGetDeleteAlbumRecommendation() {

        AlbumRecommendation album = new AlbumRecommendation();
        album.setAlbumId(1);
        album.setUserId(1);
        album.setLiked(true);

        album = albumRepository.save(album);

        Optional<AlbumRecommendation> found = albumRepository.findById(album.getAlbumRecommendationId());

        assertEquals(found.get(), album);

        albumRepository.deleteById(album.getAlbumRecommendationId());

        found = albumRepository.findById(album.getAlbumRecommendationId());

        assertFalse(found.isPresent());
    }

    @Test
    public void updateAlbumRecommendation() {

        AlbumRecommendation album = new AlbumRecommendation();
        album.setAlbumRecommendationId(1);
        album.setAlbumId(1);
        album.setUserId(1);
        album.setLiked(true);

        album = albumRepository.save(album);

        album.setAlbumRecommendationId(1);
        album.setAlbumId(1);
        album.setUserId(1);
        album.setLiked(false);

        album = albumRepository.save(album);

        Optional<AlbumRecommendation> album1 = albumRepository.findById(album.getAlbumRecommendationId());
        assertEquals(album1.get(), album);
    }

    @Test
    public void getAllAlbumRecommendations() {

        AlbumRecommendation album = new AlbumRecommendation();
        album.setAlbumRecommendationId(1);
        album.setAlbumId(1);
        album.setUserId(1);
        album.setLiked(true);

        albumRepository.save(album);

        AlbumRecommendation album2 = new AlbumRecommendation();
        album2.setAlbumId(2);
        album2.setUserId(2);
        album2.setLiked(false);

        albumRepository.save(album2);

        List<AlbumRecommendation> aList = albumRepository.findAll();
        assertEquals(aList.size(), 2);

    }
}