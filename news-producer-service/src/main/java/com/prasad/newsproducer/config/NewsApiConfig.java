package com.prasad.newsproducer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "news.api")
public class NewsApiConfig {

    private String url;
    private String key;
    private String query;
    private String lang;
    private int max;

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }

    public String getQuery() { return query; }
    public void setQuery(String query) { this.query = query; }

    public String getLang() { return lang; }
    public void setLang(String lang) { this.lang = lang; }

    public int getMax() { return max; }
    public void setMax(int max) { this.max = max; }
}