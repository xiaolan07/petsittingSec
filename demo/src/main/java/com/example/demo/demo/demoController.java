package com.example.demo.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jixia
 * @Description TODO
 * @date 22/01/2024-20:49
 */
@RestController
@RequestMapping("/api/vi/demo-controller")
public class demoController {

    @GetMapping
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("hello, from secured endpoint");
    }
}
