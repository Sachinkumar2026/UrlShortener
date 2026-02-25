package com.example.urlshortener.service;

import com.example.urlshortener.model.Url;
import com.example.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class UrlService {
    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository){
        this.urlRepository = urlRepository;
    }
    public String createShortUrl(String originalUrl){
        String shortCode = generateShortCode();

        Url url = Url.builder()
                .originalUrl(originalUrl)
                .shortCode(shortCode)
                .createAt(LocalDateTime.now())
                .expiryDate(LocalDateTime.now().plusDays(30))
                .clickCount(0L)
                .build();
        urlRepository.save(url);
        return "http://localhost:8080/" + shortCode;
    }
    private String generateShortCode(){
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for(int i = 0;i < 6;i++){
            code.append(chars.charAt(random.nextInt(chars.length())));
        }
        return code.toString();
    }

    public String getOriginalUrl(String shortCode){
        Url url = urlRepository.findByShortCode(shortCode).orElseThrow(() -> new RuntimeException("URL not found"));

        if(url.getExpiryDate().isBefore(LocalDateTime.now())){
            throw new RuntimeException("URL expired");
        }
        url.setClickCount(url.getClickCount() + 1);
        urlRepository.save(url);
        return url.getOriginalUrl();
    }
}
