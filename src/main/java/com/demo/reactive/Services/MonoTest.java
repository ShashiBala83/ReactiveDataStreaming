package com.demo.reactive.Services;

import com.demo.reactive.Configs.ElasticSearchConfig;
import com.demo.reactive.POJOs.ElasticData;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MonoTest {

    @Autowired
    ElasticSearchConfig elasticSearchConfig;

    public Mono<IndexResponse> indexDoc(ElasticData doc) {

//        IndexRequest indexRequest = new IndexRequest("first_index","name",doc.getName());
//        indexRequest.source(doc.getJson(),XContentType.JSON);

        return Mono.create(monoSink -> {
            IndexRequest indexRequest = new IndexRequest("sqloperations");
            indexRequest.source(doc.getJson(), XContentType.JSON);
            elasticSearchConfig.getClient().indexAsync(indexRequest, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
                @Override
                public void onResponse(IndexResponse indexResponse) {
                    monoSink.success();
                }

                @Override
                public void onFailure(Exception e) {
                    monoSink.error(e);
                }
            });
        });
    }
}
