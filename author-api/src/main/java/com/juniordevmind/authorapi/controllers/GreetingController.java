package com.juniordevmind.authorapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juniordevmind.shared.models.HelloWorld;

@RestController
public class GreetingController {
    @GetMapping("/hello-world")
    public ResponseEntity<String> getHealth() {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.setGreeting("Hello World");
        return ResponseEntity.ok(helloWorld.getGreeting());
    }
}
