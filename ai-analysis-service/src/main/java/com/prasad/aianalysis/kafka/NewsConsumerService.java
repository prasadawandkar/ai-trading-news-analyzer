package com.prasad.aianalysis.kafka;

import com.market.news.NewsEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.prasad.aianalysis.service.AIAnalysisService;

@Slf4j
@Service
public class NewsConsumerService {

    private final AIAnalysisService aiAnalysisService;

    public NewsConsumerService(AIAnalysisService aiAnalysisService) {
        this.aiAnalysisService = aiAnalysisService;
    }

    @KafkaListener(topics = "raw-news", groupId = "ai-analysis-group-1")
    public void consume(NewsEvent event) {
        log.info("Raw News received ... " + event.getHeadline());
        aiAnalysisService.analyze(event);

    }
}
