package PD2022.PDTicketApi.controllers;

import PD2022.PDTicketApi.security.TokenService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {


    private final TokenService tokenService;

    public AuthController(TokenService tokenService)
    {
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public String login(Authentication authentication)
    {
        return tokenService.generateToken(authentication);
    }

    @GetMapping("/test")
    public String test(Authentication authentication)
    {
        return authentication.getName();
    }
    @GetMapping("/xxx")
    public String xxx()
    {
        return "ok";
    }
}
