package com.swansong.productservice;

import com.swansong.productservice.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
//  We create a `@SpringBootTest`, starting an actual server on a `RANDOM_PORT`
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceControllerAndMongoDBTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreateProductAndFindById() {
        Product product = new Product(
                "123", "FuzzyBunnySlippers", "Pink fuzzy slippers with bunny ears", new BigDecimal("9.99"));
        webTestClient.post()
                .uri("/api/product")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(product), Product.class)
                .exchange()
                .expectStatus().isCreated();


        webTestClient.get()
                .uri("/api/product/123")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                // and use the dedicated DSL to test assertions against the response
                .expectStatus().isOk()
                .expectBody(Product.class).value(product1 ->
                {
                    assertThat(product.equals(product1)).isTrue();
                });

    }

}
