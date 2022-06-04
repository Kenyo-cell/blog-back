package ru.blogback.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.blogback.entity.User;
import ru.blogback.service.EntryService;

@RestController
@AllArgsConstructor
public class EntryController {
    private EntryService entryService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String token = entryService.login(user);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, token)
                .body("{ \"status\" : \"success\" }");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        return ResponseEntity.ok(entryService.register(user));
    }
}
