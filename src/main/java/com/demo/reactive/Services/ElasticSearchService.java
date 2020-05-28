package com.demo.reactive.Services;

import com.demo.reactive.Configs.ElasticSearchConfig;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ElasticSearchService {

    @Autowired
    private ElasticSearchConfig elasticSearchConfig;

    public List<Map<String, Object>> findAll() {
        List<Map<String, Object>> result = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest("sqloperations");
//        RestHighLevelClient client = elasticSearchConfig.getClient();
        RestHighLevelClient client = elasticSearchConfig.getRestClient();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if (!Objects.nonNull(client)) {
            System.out.println("Client is Null");
        }
        try {
            SearchResponse searchResponse;
            try {
                searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            } catch (Exception e) {
                return result;
            }
            TotalHits totalCount = searchResponse.getHits().getTotalHits();
            Long totalHits = searchResponse.getHits().getTotalHits().value;
            searchSourceBuilder.size(Math.toIntExact(totalHits));
            searchRequest.source(searchSourceBuilder);
            SearchResponse totalRecord = client.search(searchRequest, RequestOptions.DEFAULT);
            totalRecord.getHits().forEach(data -> result.add(data.getSourceAsMap()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public RestStatus insertDocument(String index, HashMap<String, Object> data) throws IOException {
        data.put("id","0987654321234567890");
        IndexRequest request = new IndexRequest(index);
        request.source(data);
        IndexResponse indexResponse = null;
        try {
            indexResponse = elasticSearchConfig.getClient().index(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw e;
        }
        return indexResponse.status();
    }
}
