package com.prasad.aianalysis.kafka;

import com.market.news.AnalyzedNewsEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, AnalyzedNewsEvent> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, AnalyzedNewsEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishAnalyzedNews(AnalyzedNewsEvent event) {
        log.info("Analysed news published ..." + event.getHeadline());
        kafkaTemplate.send("analyzed-news", event.getId(), event);
    }
}
