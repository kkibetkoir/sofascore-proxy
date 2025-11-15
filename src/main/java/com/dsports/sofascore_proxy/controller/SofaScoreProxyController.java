package com.dsports.sofascore_proxy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SofaScoreProxyController {

    private final WebClient webClient;

    public SofaScoreProxyController() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.sofascore.com")
                .defaultHeader("User-Agent", "Mozilla/5.0 (Android LiveFutaa)")
                .defaultHeader("Accept", "application/json")
                .build();
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("SofaScore Proxy API is running");
    }

    @GetMapping("/v1/sport/{sport}/events/live")
    public Mono<ResponseEntity<String>> getLiveEvents(@PathVariable String sport) {
        return webClient.get()
                .uri("/api/v1/sport/{sport}/events/live", sport)
                .retrieve()
                .toEntity(String.class);
    }

    @GetMapping("/v1/sport/{sport}/scheduled-events/{date}")
    public Mono<ResponseEntity<String>> getScheduledEvents(
            @PathVariable String sport,
            @PathVariable String date) {
        return webClient.get()
                .uri("/api/v1/sport/{sport}/scheduled-events/{date}", sport, date)
                .retrieve()
                .toEntity(String.class);
    }
}