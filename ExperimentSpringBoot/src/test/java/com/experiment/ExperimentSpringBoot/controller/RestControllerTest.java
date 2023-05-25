package com.experiment.ExperimentSpringBoot.controller;

import com.experiment.ExperimentSpringBoot.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RestControllerTest extends AbstractRestControllerTest {


    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    public void createProduct() throws Exception {
        String uri = "/Product";
        Product product = new Product();
        product.setId(3);
        product.setProductName("Ginger");
        String inputJson = super.mapToJson(product);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Successfully added");
    }

    @Test
    public void getProductsList() throws Exception {
        createProduct();
        String uri = "/Product";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Map productlist = super.mapFromJson(content, Map.class);
        assertTrue(!productlist.isEmpty());
    }
}
