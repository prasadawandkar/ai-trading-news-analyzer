package com.prasad.newsproducer.service;

import com.market.news.NewsEvent;
import com.prasad.newsproducer.config.NewsApiConfig;
import com.prasad.newsproducer.dto.GNewsResponse;
import com.prasad.newsproducer.kafka.KafkaProducerService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class NewsFetchService {

    private final KafkaProducerService kafkaProducerService;
    private final RestTemplate restTemplate;
    private final NewsApiConfig config;


    public NewsFetchService(KafkaProducerService kafkaProducerService,
                            RestTemplate restTemplate, NewsApiConfig config){
        this.kafkaProducerService = kafkaProducerService;
        this.restTemplate = restTemplate;
        this.config = config;
    }

    public void fetchAndPublishNews() {

        String url = config.getUrl()
                + "?q=" + config.getQuery()
                + "&lang=" + config.getLang()
                + "&max=" + config.getMax()
                + "&apikey=" + config.getKey();

        GNewsResponse response =
                restTemplate.getForObject(url, GNewsResponse.class);

        if (response == null || response.getArticles() == null) {
            return;
        }

        response.getArticles().forEach(article -> {

            NewsEvent event = NewsEvent.newBuilder()
                    .setId(UUID.randomUUID().toString())
                    .setHeadline(article.getTitle() != null ? article.getTitle() : "")
                    .setContent(article.getContent() != null ? article.getContent() : article.getDescription())
                    .setSource(article.getSource() != null ? article.getSource().getName() : "unknown")
                    .setPublishedAt(article.getPublishedAt() != null ? article.getPublishedAt() : "")
                    .build();

            kafkaProducerService.publishNews(event);
        });

    }
}
