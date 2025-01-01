package io.github.victor_vn.request_scope_bean_poc.controller;


import io.github.victor_vn.request_scope_bean_poc.annotation.CustomAnnotation;
import io.github.victor_vn.request_scope_bean_poc.domain.Product;
import io.github.victor_vn.request_scope_bean_poc.domain.TestSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CustomAnnotation(value = "ent", number = 1)
public class ApiV1 {

    private static final Logger logger = LoggerFactory.getLogger(ApiV1.class);
    @Autowired
    Product product;

    @Autowired
    TestSingleton singleton;

    @GetMapping
    public ResponseEntity<String> getInfo(@RequestParam String value){
        logger.info("Produto: {}", product);
        logger.info("Singleton: {}", singleton);
        return ResponseEntity.ok("Value is: " + value);
    }
}
