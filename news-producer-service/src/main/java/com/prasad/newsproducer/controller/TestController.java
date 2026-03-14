package com.prasad.newsproducer.controller;

import com.prasad.newsproducer.service.NewsFetchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final NewsFetchService newsFetchService;

    public TestController(NewsFetchService newsFetchService) {
        this.newsFetchService = newsFetchService;
    }

    @GetMapping("/test")
    public void test(){
        newsFetchService.fetchAndPublishNews();
    }
}
