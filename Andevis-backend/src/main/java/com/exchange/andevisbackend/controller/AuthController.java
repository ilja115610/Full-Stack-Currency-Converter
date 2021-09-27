package com.exchange.andevisbackend.controller;

import com.exchange.andevisbackend.DTO.AuthRequest;
import com.exchange.andevisbackend.DTO.LoginResponse;
import com.exchange.andevisbackend.config.SecurityUser;
import com.exchange.andevisbackend.config.UserDetailsServiceImpl;
import com.exchange.andevisbackend.entity.User;
import com.exchange.andevisbackend.service.UserAccountService;
import com.exchange.andevisbackend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final UserAccountService userAccountService;

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(UserAccountService userAccountService, UserDetailsServiceImpl userDetailsService, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userAccountService = userAccountService;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/auth")
    public LoginResponse generateToken(@RequestBody AuthRequest authRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            return new LoginResponse("Wrong username or password");
        }
        LoginResponse response = new LoginResponse();
        response.setAuthToken(jwtUtil.generateToken(authRequest.getLogin()));

        response.setMessage("Success");

        User user = (SecurityUser) userDetailsService.loadUserByUsername(authRequest.getLogin());
        Authentication auth =
                new UsernamePasswordAuthenticationToken(user, null);

        SecurityContextHolder.getContext().setAuthentication(auth);

        response.setUsername(user.getLogin());
        response.setUserId(user.getId());

        return response;
    }



    @PostMapping(value="/register")
    public LoginResponse postRegister (@RequestBody User user) {


        user = userAccountService.saveUser(user);

        Authentication auth =
                new UsernamePasswordAuthenticationToken(user, null);

        SecurityContextHolder.getContext().setAuthentication(auth);

        LoginResponse response = new LoginResponse();
        response.setMessage("Welcome");

        User e = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(e.getLogin()+" registered");

        return response;
    }
}
