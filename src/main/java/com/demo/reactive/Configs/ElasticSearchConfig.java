package com.demo.reactive.Configs;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;


@Service
public class ElasticSearchConfig {

    //    @Bean(destroyMethod = "close")
    public RestHighLevelClient getRestClient() {
        RestHighLevelClient restHighLevelClient = null;
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("localhost", 9200, "http"))
                .setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder.setConnectTimeout(0)
                        .setSocketTimeout(0));

        restHighLevelClient = new RestHighLevelClient(restClientBuilder);

        return restHighLevelClient;
    }

    public RestHighLevelClient getClient() {

        return new RestHighLevelClient(
                RestClient
                        .builder(new HttpHost("localhost", 9200))
                        .setRequestConfigCallback(config -> config
                                .setConnectTimeout(5_000)
                                .setConnectionRequestTimeout(5_000)
                                .setSocketTimeout(5_000)
                        )/*.setMaxRetryTimeoutMillis(5_000)*/);

    }


}
