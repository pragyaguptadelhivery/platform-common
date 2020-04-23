package document;

import client.ElasticConfig;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import reactor.core.publisher.Mono;

public class IndexApi {

    public IndexResponse indexSync(
            final String index,
            final Map<String, Object> document) {
        try (final RestHighLevelClient client = ElasticConfig.create()) {
            final IndexRequest request = new IndexRequest(index)
                    .source(document);

            return client.index(request, RequestOptions.DEFAULT);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    Mono<IndexResponse> index(final String index, final Map<String, Object> document) {
        return indexDoc(index, document);
    }


    private Mono<IndexResponse> indexDoc(final String index, final Map<String, Object> document) {
        return Mono.create(sink -> {
            try {
                indexAsync(index, document);
            } catch ( InterruptedException e) {
                sink.error(e);
            }
        });
    }
    public void indexAsync(
            final String index,
            final Map<String, Object> document) throws InterruptedException {
        try (final RestHighLevelClient client = ElasticConfig.create()) {
            final IndexRequest request = new IndexRequest(index)
                    .source(document);

            client.indexAsync(request, RequestOptions.DEFAULT, new ActionListener<>() {
                        @Override
                        public void onResponse(final IndexResponse indexResponse) {
                            // Let's do something!
                        }

                        @Override
                        public void onFailure(final Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
            );

            // JUnit When started with, the thread ends before the completion of asynchronous processing, so sleep is added.
            TimeUnit.SECONDS.sleep(1);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
}
