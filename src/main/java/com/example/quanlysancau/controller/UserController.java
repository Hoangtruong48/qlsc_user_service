package com.example.quanlysancau.controller;

import com.example.quanlysancau.request.IntrospectRequest;
import com.example.quanlysancau.request.RegisterAccountRequest;
import com.example.quanlysancau.request.UserLoginRequest;
import com.example.quanlysancau.service.UserService;
import com.nimbusds.jose.JOSEException;
import com.qlsc.qlsc_common.response.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserController {
    UserService userService;
    @GetMapping("/get")
    public ApiResponse<?> getUser(@RequestParam String username) {
        return userService.getUserByUserName(username);
    }
    @PostMapping("/register")
    public ApiResponse<?> registerAccount(@RequestBody RegisterAccountRequest request) {
        return userService.registerAccount(request);
    }

    @PostMapping("/log-in")
    public ApiResponse<?> authenticate(@RequestBody UserLoginRequest request) {
        return userService.authenticate(request);
    }

    @PostMapping("/introspect")
    public ApiResponse<?> introspect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        return userService.introSpectToken(request);
    }

    @GetMapping("/find-all")
    public ApiResponse<?> findAllUser() {
        return userService.findAllUser();
    }

}

