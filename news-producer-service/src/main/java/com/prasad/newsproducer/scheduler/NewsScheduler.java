package com.prasad.newsproducer.scheduler;

import com.prasad.newsproducer.service.NewsFetchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NewsScheduler {

    private final NewsFetchService newsFetchService;

    public NewsScheduler(NewsFetchService newsFetchService) {
        this.newsFetchService = newsFetchService;
    }

    @Scheduled(fixedRate = 60000)
    public void fetchNews() {
        log.info("fetching latest news ... ");
        newsFetchService.fetchAndPublishNews();
    }

}
