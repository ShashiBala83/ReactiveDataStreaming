package com.demo.reactive.Controllers;

import com.demo.reactive.POJOs.ElasticData;
import com.demo.reactive.Services.MonoTest;
import com.demo.reactive.Services.ReactiveService;
import org.elasticsearch.action.index.IndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/reactive")
public class ReactiveController {

    @Autowired
    ReactiveService reactiveService;

    @Autowired
    MonoTest monoTest;

    @GetMapping(path = "/flux")
    public void getFlux() {
        reactiveService.fluxTestWithSubscribe();
    }

    @GetMapping(path = "/mono")
    public void getMono() {
        reactiveService.monoTest();
    }

    @PutMapping(path = "/sink")
    public Mono<IndexResponse> checkMonoSink(@RequestBody ElasticData document) {
        return monoTest.indexDoc(document);
    }


}
