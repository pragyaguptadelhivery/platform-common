package client;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class ElasticConfig {

    public static RestHighLevelClient create() {
        return new RestHighLevelClient(RestClient.builder(

		//TO BE PICKED from application properties file
                new HttpHost("localhost", 9200, "http")
        ));
    }
}
