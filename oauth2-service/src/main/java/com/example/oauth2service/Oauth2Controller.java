package com.example.oauth2service;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class Oauth2Controller {

    @GetMapping("/user")
    public String currentUserName(OAuth2AuthenticationToken token) {
        return token.toString();
    }
}
