package com.ms.maxbot.api;

import jakarta.ws.rs.core.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class WebClientBase<T> {
    private final WebClient webClient;
    private final Class<T> tClass;

    public WebClientBase(String baseUrl, Class<T> tClass) {
        webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .build();
        this.tClass = tClass;
    }

    public Mono<T> getMono(String uri) {
        return retrieve(uri).bodyToMono(tClass);
    }

    public Flux<T> getFlux(String uri) {
        return retrieve(uri).bodyToFlux(tClass);
    }

    private ResponseSpec retrieve(String uri) {
        return webClient.get().uri(uri).retrieve();
    }

}
