package com.prasad.newsproducer.kafka;


import com.market.news.NewsEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, NewsEvent> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, NewsEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishNews(NewsEvent event) {
        log.info("Publishing the news ..."+ event.getHeadline());
        kafkaTemplate.send("raw-news", event.getId().toString(), event);
    }
}
