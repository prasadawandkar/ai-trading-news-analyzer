package com.prasad.aianalysis.service;

import com.market.news.AnalyzedNewsEvent;
import com.market.news.NewsEvent;
import com.prasad.aianalysis.kafka.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AIAnalysisService {

    private final KafkaProducerService kafkaProducerService;

    public AIAnalysisService(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    public void analyze(NewsEvent event) {

        log.debug("Analyzizng the raw news using AI models.. " + event.getId());

        String headline = event.getHeadline().toLowerCase();

        double score = 0.0;
        String sentiment = "NEUTRAL";

        if(headline.contains("surge") || headline.contains("gain") || headline.contains("rise"))
        {
            score = 0.8;
            sentiment = "POSITIVE";
        }
        else if(headline.contains("drop") || headline.contains("fall") || headline.contains("loss"))
        {
            score = -0.8;
            sentiment = "NEGATIVE";
        }

        String company = extractCompany(headline);

        AnalyzedNewsEvent analyzedEvent =
                AnalyzedNewsEvent.newBuilder()
                        .setId(event.getId())
                        .setHeadline(event.getHeadline())
                        .setCompany(company)
                        .setSentimentScore(score)
                        .setSentimentLabel(sentiment)
                        .setPublishedAt(event.getPublishedAt())
                        .build();

        log.info("Analyzed Event: " + analyzedEvent);
        kafkaProducerService.publishAnalyzedNews(analyzedEvent);
    }

    private String extractCompany(String text) {

        if(text.contains("tesla")) return "Tesla";
        if(text.contains("nvidia")) return "Nvidia";
        if(text.contains("apple")) return "Apple";
        if(text.contains("amazon")) return "Amazon";

        return null;
    }
}
