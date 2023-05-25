package com.experiment.ExperimentSpringBoot.model;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

@Document
public class Product {

    @Id
    private long id;

    @Version
    private long version;

    @Field
    @NotNull
    private String productName;

    public void setId(long id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String toString() {
        return "id: "+ id +" version:" + version + " productName:"+productName;
    }
}
