package io.github.victor_vn.request_scope_bean_poc.controller;


import io.github.victor_vn.request_scope_bean_poc.annotation.EnablePrePosHandling;
import io.github.victor_vn.request_scope_bean_poc.domain.ProcessDto;
import io.github.victor_vn.request_scope_bean_poc.domain.TestSingleton;
import io.github.victor_vn.request_scope_bean_poc.service.UserService;
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
@EnablePrePosHandling
public class ApiV1 {
    @Autowired
    ProcessDto processDto;

    @Autowired
    UserService service;

    @Autowired
    TestSingleton singleton;

    @GetMapping
    public ResponseEntity<String> getInfo(@RequestParam String value){
        service.processDto();
        return ResponseEntity.ok("Value is: " + processDto.getOutput());
    }

    @GetMapping("/not")
    public ResponseEntity<String> notAnnotaded(@RequestParam String value){
        return ResponseEntity.ok("not annotaded endpoint");
    }
}
