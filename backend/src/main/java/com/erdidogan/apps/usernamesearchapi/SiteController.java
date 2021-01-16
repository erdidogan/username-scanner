package com.erdidogan.apps.usernamesearchapi;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api")
@RequiredArgsConstructor
@CrossOrigin
public class SiteController {

    private final SiteService siteService;

    @GetMapping("find")
    public ResponseEntity<List<SiteResponseModel>> search(@RequestParam String username) {
        username = username.replaceAll("[^a-zA-Z0-9-_.]/g", "");
        if (username.length() >= 4) {
            return ResponseEntity.ok().body(siteService.findAll(username));
        } else {
            return ResponseEntity.badRequest().body(new ArrayList<>());
        }

    }

}
