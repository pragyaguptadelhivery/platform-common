package client.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticConfig {
    @Value("${elasticsearch.host:localhost}")
    private static String host;

    @Value("${elasticsearch.port:9300}")
    private static int port;

    @Value("${elasticsearch.timeout:6000}")
    private static int timeout;

    public String getHost() {
        return host;
    }
    public int getPort() {
        return port;
    }

    @Bean
    public static RestHighLevelClient client(){

        System.out.println("host:"+ host+"port:"+port);
        RestClientBuilder builder =RestClient.builder(new HttpHost("localhost", 9300, "http"));
        builder.setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder.setConnectTimeout(60 * 1000).setSocketTimeout(60 * 1000));

        return new RestHighLevelClient(builder);
    }
}
