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
public class LabelRecommendationRepositoryTest {

    @Autowired
    LabelRecommendationRepository labelRepository;

    @Before
    public void setUp() throws Exception {
        labelRepository.deleteAll();
    }

    @Test
    public void addGetDeleteLabelRecommendation() {

        LabelRecommendation label = new LabelRecommendation();
        label.setLabelId(1);
        label.setUserId(1);
        label.setLiked(true);

        label = labelRepository.save(label);

        Optional<LabelRecommendation> label1 = labelRepository.findById(label.getLabelRecommendationId());

        assertEquals(label1.get(), label);

        labelRepository.deleteById(label.getLabelRecommendationId());

        label1 = labelRepository.findById(label.getLabelRecommendationId());

        assertFalse(label1.isPresent());
    }

    @Test
    public void updateLabelRecommendation() {

        LabelRecommendation label = new LabelRecommendation();
        label.setLabelId(1);
        label.setUserId(1);
        label.setLiked(true);

        labelRepository.save(label);

        label.setLabelId(1);
        label.setUserId(1);
        label.setLiked(false);

        labelRepository.save(label);

        Optional<LabelRecommendation> label1 = labelRepository.findById(label.getLabelRecommendationId());
        assertEquals(label1.get(), label);
    }

    @Test
    public void getAllLabelsRecommendation() {

        LabelRecommendation label = new LabelRecommendation();
        label.setLabelId(1);
        label.setUserId(1);
        label.setLiked(true);

        label = labelRepository.save(label);

        LabelRecommendation label1 = new LabelRecommendation();
        label1.setLabelId(2);
        label1.setUserId(2);
        label1.setLiked(false);

        label = labelRepository.save(label1);

        List<LabelRecommendation> lList = labelRepository.findAll();
        assertEquals(lList.size(), 2);

    }

}