package com.experiment.ExperimentSpringBoot.couchconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.CouchbaseClientFactory;
import org.springframework.data.couchbase.SimpleCouchbaseClientFactory;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.convert.CouchbaseCustomConversions;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import java.util.Collections;

@Configuration
@EnableCouchbaseRepositories
public class CouchDBbaseConfig extends AbstractCouchbaseConfiguration {

    @Value("${app.couchbase.connection-string}")
    private String connectionString;

    @Value("${app.couchbase.user-name}")
    private String userName;

    @Value("${app.couchbase.password}")
    private String password;

    @Value("${app.couchbase.primary-bucket}")
    private String primaryBucket;

    @Override
    public String getConnectionString() {
        return connectionString;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getBucketName() {
        return primaryBucket;
    }

    // use it for user separate bucket
    /*@Override
    protected void configureRepositoryOperationsMapping(RepositoryOperationsMapping mapping) {
        mapping.mapEntity(Product.class, getCouchbaseTemplate(userbucket));
    }*/

    private CouchbaseTemplate getCouchbaseTemplate(String bucketName) {
        CouchbaseTemplate template = null;
        try {
            template = new CouchbaseTemplate(
                    getCouchbaseClientFactory(bucketName),
                    mappingCouchbaseConverter(
                            couchbaseMappingContext(customConversions()),
                            new CouchbaseCustomConversions(Collections.emptyList())
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return template;
    }

    private CouchbaseClientFactory getCouchbaseClientFactory(String bucketName) {
        return new SimpleCouchbaseClientFactory(
                couchbaseCluster(couchbaseClusterEnvironment()),
                bucketName,
                getScopeName());
    }
}
