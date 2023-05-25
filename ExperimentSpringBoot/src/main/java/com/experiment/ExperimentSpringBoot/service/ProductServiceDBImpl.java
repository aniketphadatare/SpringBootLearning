package com.experiment.ExperimentSpringBoot.service;

import com.couchbase.client.core.error.DocumentExistsException;
import com.experiment.ExperimentSpringBoot.model.Product;
import com.experiment.ExperimentSpringBoot.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceDBImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    Logger logger = LoggerFactory.getLogger(ProductServiceDBImpl.class);
    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public String updateProduct(Long id, Product product) {
        Optional<Product> dbProduct = productRepository.findById(id);
        logger.info("updateProduct : " +dbProduct.get().toString());
        if(productRepository.findById(id) != null) {
            try {
                dbProduct.get().setProductName(product.getProductName());
                productRepository.save(dbProduct.get());
            } catch(OptimisticLockingFailureException ex) {
                logger.info("updateProduct : " + ex.getMessage());
            }
            return "Successfully updated";
        } else {
            productRepository.save(product);
            return "Successfully newly created";
        }
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public HashMap<Long, Product> getProducts() {
        List<Product> productList = productRepository.findAll();
        HashMap<Long, Product> productMap = new HashMap<>();
        for(Product product : productList) {
            productMap.put(product.getId(), product);
        }
        return productMap;
    }

    @Override
    public Product getProducts(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Long getProductCount() {
        return productRepository.countProduct();
    }
}
