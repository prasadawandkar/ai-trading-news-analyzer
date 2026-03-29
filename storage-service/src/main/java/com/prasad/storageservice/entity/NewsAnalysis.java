package com.prasad.storageservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class NewsAnalysis {

    @Id
    private String id;

    private String headline;
    private String company;
    private double sentimentScore;
    private String sentimentLabel;
    private String publishedAt;
}