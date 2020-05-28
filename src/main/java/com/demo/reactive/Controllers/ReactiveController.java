package com.demo.reactive.Controllers;

import com.demo.reactive.POJOs.ElasticData;
import com.demo.reactive.Services.MonoTest;
import com.demo.reactive.Services.ReactiveService;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
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
    public void getFlux(@RequestParam(required = true) String index) {
        reactiveService.fluxTestWithSubscribe(index);
    }

    @GetMapping(path = "/mono")
    public void getMono() {
        reactiveService.monoTest();
    }

    @RequestMapping(path = "/sink", method = RequestMethod.GET)
    public Mono<SearchResponse> checkMonoSink(@RequestBody ElasticData document, @RequestParam(required = true) String index) {
        return monoTest.indexDoc(document, index);
    }


}
