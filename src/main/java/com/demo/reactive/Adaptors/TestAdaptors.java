/*
package com.demo.reactive;

import com.demo.reactive.POJOs.ElasticData;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.mapper.ObjectMapper;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

@Component
@RequiredArgsConstructor
public class Adaptors {

    private  RestHighLevelClient client;
    private  ObjectMapper objectMapper;

    Mono<IndexResponse> index(ElasticData data) {
        return indexDoc(doc);
    }

    private void doIndex(ElasticData data, ActionListener<IndexResponse> listener) throws JsonProcessingException {
        return Mono.create(sink -> {
            try {
                doIndex(data, listenerToSink(sink));
            } catch (JsonProcessingException e) {
                sink.error(e);
            }
        });
    }

    private void doIndex(ElasticData data, ActionListener<IndexResponse> listener) throws JsonProcessingException {
        final IndexRequest indexRequest = new IndexRequest("first_index", "person", data.getName());
        final String json = objectMapper.writeValueAsString(data);
        indexRequest.source(json, XContentType.JSON);
        client.indexAsync(indexRequest, listener);
    }

    private <T> ActionListener<T> listenerToSink(MonoSink<T> sink) {
        return new ActionListener<T>() {
            @Override
            public void onResponse(T response) {
                sink.success(response);
            }

            @Override
            public void onFailure(Exception e) {
                sink.error(e);
            }
        };
    }


}
*/
