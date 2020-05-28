package com.demo.reactive.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class ReactiveService {

    @Autowired
    ElasticSearchService elasticSearchService;

    public void fluxTestWithSubscribe() {
        Flux<List<Map<String, Object>>> fluxString = Flux.just(elasticSearchService.findAll()).log();
//        fluxString.subscribe(System.out::println, (error) -> System.err.println(error), () -> System.out.println("Complete"));
         fluxString.subscribe();

    }

    /*public void fluxTestWithStepVerifier() {
        Flux<String> fluxString = Flux.just("spring", "spring boot", "reactive spring").log();
        reactor.test.StepVerifier
                .create(fluxString)
                .expectNext("Spring")
                .expectNext("spring boot")
                .expectNext("reactive spring")
                .verifyComplete();

    }*/


    public void monoTest() {
        Mono<String> monoSpring = Mono.just("Spring").log();

        monoSpring.subscribe(System.out::println);
    }

}
