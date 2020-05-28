package com.demo.reactive.Adaptors;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.search.profile.Timer;
import org.springframework.data.geo.Metrics;
import org.w3c.dom.css.Counter;

import java.util.concurrent.atomic.LongAdder;

public class HelperAdaptor {
   /* private final Timer indexTimer = Metrics.timer("es.timer");
    private final LongAdder concurrent = Metrics.gauge("es.concurrent", new LongAdder());
    private final Counter successes = Metrics.counter("es.index", "result", "success");
    private final Counter failures = Metrics.counter("es.index", "result", "failure");

    private Mono<IndexResponse> countSuccFail(Mono<IndexResponse> mono) {
        return mono
                .doOnError(e -> failures.increment())
                .doOnSuccess(response -> successes.increment());
    }

    private Mono<IndexResponse> countConcurrent(Mono<IndexResponse> mono) {
        return mono
                .doOnSubscribe(s -> concurrent.increment())
                .doOnTerminate(concurrent::decrement);
    }

    private Mono<IndexResponse> measureTime(Mono<IndexResponse> mono) {
        return Mono
                .fromCallable(System::currentTimeMillis)
                .flatMap(time ->
                        mono.doOnSuccess(response ->
                                indexTimer.record(System.currentTimeMillis() - time, TimeUnit.MILLISECONDS))
                );
    }*/
}
