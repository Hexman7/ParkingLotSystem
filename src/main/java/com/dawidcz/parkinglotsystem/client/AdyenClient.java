package com.dawidcz.parkinglotsystem.client;

import com.dawidcz.parkinglotsystem.dto.AuthoriseRequest;
import com.dawidcz.parkinglotsystem.dto.AuthoriseResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AdyenClient {

    private final WebClient webClient;

    public AdyenClient(WebClient adyenWebClient) {
        this.webClient = adyenWebClient;
    }

    public AuthoriseResponse authorise(AuthoriseRequest request) {

        return webClient.post()
                .uri("/authorise")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(AuthoriseResponse.class)
                .block();
    }
}