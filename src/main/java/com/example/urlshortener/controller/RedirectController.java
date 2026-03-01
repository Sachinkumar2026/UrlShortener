package com.example.urlshortener.controller;

import com.example.urlshortener.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class RedirectController {
    private final UrlService urlService;
    public RedirectController(UrlService urlService){
        this.urlService = urlService;
    }

    @GetMapping("/{shortCode:[a-zA-Z0-9]+}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode, HttpServletRequest request){
        String ip = request.getRemoteAddr();

        String originalUrl = urlService.getOriginalUrl(shortCode,ip);

        return ResponseEntity
                .status(302)
                .location(URI.create(originalUrl))
                .build();
    }
}
