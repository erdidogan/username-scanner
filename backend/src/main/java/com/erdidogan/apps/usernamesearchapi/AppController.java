package com.erdidogan.apps.usernamesearchapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AppController {

    @GetMapping("/")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok().body("It Works");
    }

}
