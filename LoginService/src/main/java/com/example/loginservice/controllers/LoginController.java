package com.example.loginservice.controllers;

import com.example.loginservice.models.RequestUser;
import com.example.loginservice.models.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @GetMapping
    public User getUserByUsernameAndPassword(@RequestParam("username") String username, @RequestParam("password") String password) {
        String url = "http://localhost:8088/api/register";
        String request = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("username", username)
                .queryParam("password", password)
                .toUriString();
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(request, User.class);
    }

    @PostMapping
    public User loginUser(@RequestBody RequestUser user) {
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl("http://localhost:9191/api/login")
                .queryParam("username", user.getUsername())
                .queryParam("password", user.getPassword())
                .toUriString();
        return restTemplate.getForObject(url, User.class);
    }
}
