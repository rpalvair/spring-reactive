package com.palvair.spring.reactive.presentation.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static java.time.Duration.ofMillis;

@RestController
@RequestMapping("/reactive/flux")
public class FluxResource {

    private int maxValue = 100;

    public void setMaxValue(final int maxValue) {
        this.maxValue = maxValue;
    }

    @GetMapping("/count")
    public Flux<String> generateFlux() {
        return Flux.interval(ofMillis(100))
                .onBackpressureBuffer(maxValue)
                .map(index -> index + 1)
                .map(this::ajouterVirgule)
                .limitRequest(maxValue);
    }

    private String ajouterVirgule(final Long index) {
        if (index < maxValue) {
            return index + StringUtils.SPACE;
        }
        return String.valueOf(index);
    }
}
