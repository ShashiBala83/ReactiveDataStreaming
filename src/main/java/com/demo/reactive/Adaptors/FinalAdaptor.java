package com.demo.reactive.Adaptors;

import com.demo.reactive.Configs.ElasticSearchConfig;
import com.demo.reactive.POJOs.ElasticData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;


@Component
public class FinalAdaptor {

    private final RestHighLevelClient client;
    private final ObjectMapper objectMapper;

    @Autowired
    ElasticSearchConfig elasticSearchConfig;


    public FinalAdaptor(RestHighLevelClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    private ActionListener<IndexResponse> actionListener(MonoSink sink) {

        return new ActionListener() {
            @Override
            public void onResponse(Object o) {
                sink.success(o);
            }

            @Override
            public void onFailure(Exception e) {
                sink.error(e);
            }
        };
    }

    public void createIndexing(ElasticData data, ActionListener<IndexResponse> listener) throws JsonProcessingException {
        IndexRequest indexRequest = new IndexRequest("first_index", data.getName());
        String json = objectMapper.writeValueAsString(data);
        indexRequest.source(json, XContentType.JSON);
        elasticSearchConfig.getClient().indexAsync(indexRequest, RequestOptions.DEFAULT, listener);
    }

    public void useCreateIndexInMono(ElasticData data) {
        Mono.create(monoSink -> {
            try {
                createIndexing(data, actionListener(monoSink));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }
}
