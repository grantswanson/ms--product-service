package com.swansong.productservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.http.HttpHeaders;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestControllerTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCallTestController() {

        String expectedStr = "test999";
        webTestClient.get()
                .uri("/api/test")
                .header(HttpHeaders.CONTENT_TYPE)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).value(retStr -> assertEquals(expectedStr, retStr));

    }


}
