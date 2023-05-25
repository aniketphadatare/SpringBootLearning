package com.experiment.ExperimentSpringBoot.controller;

import com.experiment.ExperimentSpringBoot.model.Product;
import com.experiment.ExperimentSpringBoot.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.HashMap;

@RestController
public class ExperimentRestController {

    private static final Logger logger = LoggerFactory.getLogger(ExperimentRestController.class);

    @Value("${welcome.message}")
    String welcomeText;
    @Autowired
    @Qualifier("productServiceDBImpl")
    private ProductService productService;

    @GetMapping("/Product")
    //@CrossOrigin(origins = "http://test:8080")
    public HashMap<Long, Product> getExperimentData() {
        logger.debug("Experiment test is called");
        return productService.getProducts();
    }

    @GetMapping("/Product/{id}")
    public Product getExperimentData(@PathVariable("id") Long id) {
        logger.info("Experiment test is called");
        return productService.getProducts(id);
    }

    @PostMapping("/Product")
    public String addRecord(@RequestBody Product product) {
        productService.createProduct(product);
        return "Successfully added";
    }

    @PutMapping("/Product/{id}")
    public String updateRecord(@PathVariable("id") Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/Product/{id}")
    public boolean deleteRecord(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return true;
    }

    @PostMapping(value = "/Product/file/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        File convertFile = new File("/var/tmp/"+file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();
        return "File is upload successfully";
    }

    @GetMapping("/Product/download")
    public ResponseEntity<Object> downloadFile() throws FileNotFoundException {
        String filename = "/var/tmp/Screenshot 2023-05-09 at 11.16.28 AM.png";
        File file = new File(filename);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ResponseEntity<Object>
                responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(
                MediaType.parseMediaType("application/txt")).body(resource);

        return responseEntity;
    }

    @GetMapping(value = "/welcome")
    public String welcomeText() {
        return welcomeText;
    }

    @GetMapping(value = "/Product/count")
    public String getProductCount() {
        return productService.getProductCount().toString();
    }
}
