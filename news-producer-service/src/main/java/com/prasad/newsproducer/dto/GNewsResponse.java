package com.prasad.newsproducer.dto;

import java.util.List;

public class GNewsResponse {

    private List<Article> articles;

    public List<Article> getArticles() { return articles; }
    public void setArticles(List<Article> articles) { this.articles = articles; }

    public static class Article {

        private String title;
        private String description;
        private String content;
        private String publishedAt;
        private Source source;

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }

        public String getPublishedAt() { return publishedAt; }
        public void setPublishedAt(String publishedAt) { this.publishedAt = publishedAt; }

        public Source getSource() { return source; }
        public void setSource(Source source) { this.source = source; }
    }

    public static class Source {

        private String name;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }
}