package rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.infraestructure.adapters.db.cosmos;

import com.microsoft.azure.documentdb.ConnectionPolicy;
import com.microsoft.azure.documentdb.ConsistencyLevel;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.spring.data.cosmosdb.config.AbstractDocumentDbConfiguration;
import com.microsoft.azure.spring.data.cosmosdb.config.DocumentDBConfig;
import com.microsoft.azure.spring.data.cosmosdb.repository.config.EnableDocumentDbRepositories;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDocumentDbRepositories
public class CosmosDBConfiguration extends AbstractDocumentDbConfiguration {

    private final String uri;
    private final String key;
    private final String database;

    public CosmosDBConfiguration(
            @Value("${azure.cosmosdb.uri}") final String uri,
            @Value("${azure.cosmosdb.key}") final String key,
            @Value("${azure.cosmosdb.database}") final String database) {
        this.uri = uri;
        this.key = key;
        this.database = database;
    }

    public DocumentClient documentClient() {
        return new DocumentClient(uri, key, ConnectionPolicy.GetDefault(), ConsistencyLevel.Session);
    }

    public String getDatabase() {
        return database;
    }

	@Override
	public DocumentDBConfig getConfig() {
		return DocumentDBConfig.builder(this.uri, this.key, this.database).build();
	}
}
