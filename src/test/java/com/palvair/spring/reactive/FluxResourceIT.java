package com.palvair.spring.reactive;

import com.palvair.spring.reactive.infrastructure.spring.Application;
import com.palvair.spring.reactive.presentation.rest.FluxResource;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FluxResourceIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(FluxResourceIT.class);

    @Autowired
    private FluxResource fluxResource;

    @Before
    public void before() {
        fluxResource.setMaxValue(2);
    }

    @Test
    public void should() {
        WebTestClient.bindToServer()
                .baseUrl("http://localhost:8080")
                .responseTimeout(Duration.ofMillis(10000))
                .build()
                .get()
                .uri("/reactive/flux/count")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .isEqualTo("1" + StringUtils.SPACE + "2");
    }
}
