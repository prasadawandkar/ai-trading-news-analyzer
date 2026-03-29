package com.prasad.storageservice.repository;

import com.prasad.storageservice.entity.NewsAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsAnalysisRepository extends JpaRepository<NewsAnalysis, String> {
}