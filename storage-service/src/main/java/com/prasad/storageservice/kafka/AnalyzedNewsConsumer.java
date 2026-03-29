package com.prasad.storageservice.kafka;

import com.market.news.AnalyzedNewsEvent;
import com.prasad.storageservice.entity.NewsAnalysis;
import com.prasad.storageservice.repository.NewsAnalysisRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AnalyzedNewsConsumer {

    private final NewsAnalysisRepository repository;

    public AnalyzedNewsConsumer(NewsAnalysisRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "analyzed-news", groupId = "storage-group")
    public void consume(AnalyzedNewsEvent event) {

        NewsAnalysis entity = new NewsAnalysis();

        entity.setId(event.getId());
        entity.setHeadline(event.getHeadline());
        entity.setCompany(event.getCompany());
        entity.setSentimentScore(event.getSentimentScore());
        entity.setSentimentLabel(event.getSentimentLabel());
        entity.setPublishedAt(event.getPublishedAt());

        repository.save(entity);

        System.out.println("Saved to DB: " + entity.getHeadline());
    }
}
