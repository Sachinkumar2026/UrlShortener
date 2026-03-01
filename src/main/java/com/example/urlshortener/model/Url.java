package com.example.urlshortener.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalUrl;

    @Column(unique = true)
    private String shortCode;

    private LocalDateTime createAt;

    private LocalDateTime expiryDate;

    private Long clickCount = 0L;

    private String lastAccessIp;
    private String lastAccessCountry;
    private String lastAccessCity;
}
