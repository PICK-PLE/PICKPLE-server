package com.pickple.server.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/test")
    public ResponseEntity<String> responseEntity() {
        return ResponseEntity.ok("ok");
    }
}