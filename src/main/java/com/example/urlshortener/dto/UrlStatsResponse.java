package com.example.urlshortener.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UrlStatsResponse {
    private String originalUrl;
    private String shortCode;
    private Long clickCount;
    private LocalDateTime createAt;
    private  LocalDateTime expiryDate;

}
