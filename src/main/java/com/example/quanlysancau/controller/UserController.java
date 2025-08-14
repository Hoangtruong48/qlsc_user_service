package com.example.quanlysancau.controller;

import com.example.quanlysancau.request.RegisterAccountRequest;
import com.example.quanlysancau.request.UserLoginRequest;
import com.example.quanlysancau.service.UserService;
import com.example.quanlysancau.util.JwtUtil;
import com.qlsc.qlsc_common.response.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserController {
    UserService userService;
    Logger LOG = LoggerFactory.getLogger(this.getClass());
    @GetMapping("/get")
    public ApiResponse<?> getUser(@RequestParam String username) {
        LOG.info("Start api get user");
        ApiResponse<?> response = userService.getUserByUserName(username);
        LOG.info("End api get user");
        return response;
    }
    @PostMapping("/register")
    public ApiResponse<?> registerAccount(@RequestBody RegisterAccountRequest request) {
        LOG.info("Start api register");
        ApiResponse<?> response = userService.registerAccount(request);
        LOG.info("End api register");
        return response;
    }

//    @PostMapping("/log-in")
//    public ApiResponse<?> authenticate(@RequestBody UserLoginRequest request) {
//        return userService.authenticate(request);
//    }

    @GetMapping("/find-all")
    public ApiResponse<?> findAllUser() {
        LOG.info("Start api fill all user");
        ApiResponse<?> response =  userService.findAllUser();
        LOG.info("End api fill all user");
        return response;
    }

    @PostMapping("/login")
    public ApiResponse<?> login(@RequestBody UserLoginRequest request) {
        LOG.info("Start api login");
        ApiResponse<?> response =  userService.authenticate(request);
        LOG.info("End api login");
        return response;
    }

}

