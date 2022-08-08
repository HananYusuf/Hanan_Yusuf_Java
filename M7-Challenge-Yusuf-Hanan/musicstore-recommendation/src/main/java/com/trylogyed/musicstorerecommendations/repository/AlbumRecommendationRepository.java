package com.trylogyed.musicstorerecommendations.repository;

import com.trylogyed.musicstorerecommendations.model.AlbumRecommendation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRecommendationRepository extends JpaRepository<AlbumRecommendation, Integer> {
}
