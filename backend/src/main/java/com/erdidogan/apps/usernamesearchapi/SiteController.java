package com.erdidogan.apps.usernamesearchapi;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api")
@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
public class SiteController {

    private final SiteService siteService;

    @GetMapping("find")
    public ResponseEntity<?> search(@RequestParam String username) {
        username = username.replaceAll("[^a-zA-Z0-9-_.]/g", "");
        if (username.length() >= 4) {
            return ResponseEntity.ok().body(siteService.findAll(username));
        } else {
            return ResponseEntity.badRequest().body("Bad Request");
        }

    }

}
