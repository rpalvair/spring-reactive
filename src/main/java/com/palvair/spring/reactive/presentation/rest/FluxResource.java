package com.palvair.spring.reactive.presentation.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static java.time.Duration.ofMillis;

@RestController
@RequestMapping("/reactive/flux")
public class FluxResource {

    @GetMapping("/count")
    public Flux<String> generateFlux() {
        return Flux.interval(ofMillis(100))
                .onBackpressureBuffer(10)
                .map(index -> index + 1)
                .map(this::ajouterVirgule)
                .limitRequest(30);
    }

    private String ajouterVirgule(final Long index) {
        if (index < 30) {
            return index + ",";
        }
        return String.valueOf(index);
    }
}
