package com.demo.reactive.Services;

import com.demo.reactive.Configs.ElasticSearchConfig;
import com.demo.reactive.POJOs.ElasticData;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

@Service
public class MonoTest {

    @Autowired
    ElasticSearchConfig elasticSearchConfig;

    public Mono<SearchResponse> indexDoc(ElasticData doc, String index) {

//        IndexRequest indexRequest = new IndexRequest("first_index","name",doc.getName());
//        indexRequest.source(doc.getJson(),XContentType.JSON);
        Mono<SearchResponse> monoResponse = Mono.create(monoSink -> {

            SearchRequest searchRequest = new SearchRequest(index);
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            try {
                elasticSearchConfig.getClient().searchAsync(searchRequest, RequestOptions.DEFAULT, new ActionListener<SearchResponse>() {
                    @Override
                    public void onResponse(SearchResponse searchResponse) {
                        monoSink.success(searchResponse);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        monoSink.error(e);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }


           /* IndexRequest indexRequest = new IndexRequest("first_index");
            indexRequest.source(doc.getJson(), XContentType.JSON);
            try {
                elasticSearchConfig.getClient().indexAsync(indexRequest, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
                    @Override
                    public void onResponse(IndexResponse indexResponse) {
                        monoSink.success(indexResponse);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        monoSink.error(e);
                    }
                });
//                elasticSearchConfig.getClient().index(indexRequest, RequestOptions.DEFAULT);
        } catch(Exception e){
            e.printStackTrace();
        }*/
        });

        return monoResponse;


    }


}
