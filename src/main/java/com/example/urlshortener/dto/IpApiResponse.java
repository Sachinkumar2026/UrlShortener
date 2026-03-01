package com.example.urlshortener.dto;


import lombok.Data;

@Data
public class IpApiResponse {
    private String country;
    private String city;
    private String query;
}
