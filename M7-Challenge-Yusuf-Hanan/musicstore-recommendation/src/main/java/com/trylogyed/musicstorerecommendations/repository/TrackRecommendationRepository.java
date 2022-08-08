package com.trylogyed.musicstorerecommendations.repository;

import com.trylogyed.musicstorerecommendations.model.LabelRecommendation;
import com.trylogyed.musicstorerecommendations.model.TrackRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRecommendationRepository extends JpaRepository<TrackRecommendation, Integer> {
}
