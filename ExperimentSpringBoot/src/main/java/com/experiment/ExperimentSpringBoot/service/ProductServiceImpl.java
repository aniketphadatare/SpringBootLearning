package com.experiment.ExperimentSpringBoot.service;

import com.experiment.ExperimentSpringBoot.ExceptionHandling.ProductNotFoundException;
import com.experiment.ExperimentSpringBoot.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.HashMap;

@Service
public class ProductServiceImpl implements ProductService{

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private HashMap<Long, Product> productList = new HashMap<Long, Product>();

    @Override
    public void createProduct(Product product) {
        productList.put(product.getId(), product);
    }

    @Override
    public String updateProduct(Long id, Product product) {
        if(productList.get(id) != null) {
            productList.put(product.getId(), product);
            return "Successfully updated";
        } else {
            logger.info("updateRecord not found");
            throw new ProductNotFoundException();
        }
    }

    @Override
    public void deleteProduct(Long id) {
        productList.remove(id);
    }

    @Override
    public HashMap<Long, Product> getProducts() {
        return productList;
    }

    @Override
    public Product getProducts(Long id) {
        Product product = productList.get(id);
        return productList.get(id);
    }

    @Override
    public Long getProductCount() {
        return Long.getLong(String.valueOf(productList.size()));
    }

}
