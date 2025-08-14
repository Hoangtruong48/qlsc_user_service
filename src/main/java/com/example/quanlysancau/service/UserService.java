package com.example.quanlysancau.service;

import com.example.quanlysancau.entity.User;
import com.example.quanlysancau.repo.UserRepoJpa;
import com.example.quanlysancau.request.RegisterAccountRequest;
import com.example.quanlysancau.request.UserLoginRequest;
import com.example.quanlysancau.response.UserLoginResponse;
import com.example.quanlysancau.util.JwtUtil;
import com.qlsc.qlsc_common.response.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    Logger LOG = LoggerFactory.getLogger(UserService.class);
    UserRepoJpa userRepoJpa;
    PasswordEncoder passwordEncoder;
    JwtUtil jwtUtil;

    @NonFinal
    @Value("${jwt.signer_key}")
    private String signerKey;

    public ApiResponse<?> registerAccount(RegisterAccountRequest request) {
        ApiResponse<?> response = new ApiResponse<>();

        String messageError = request.validate();
        if (StringUtils.hasLength(messageError)) {
            response.setMessageFailed(messageError);
            return response;
        }

        boolean isExistUser = userRepoJpa.existsByUsername(request.getUserName());
        if (isExistUser) {
            response.setMessageFailed("Username is already taken");
            return response;
        }
        User user = request.toEntity();
        userRepoJpa.save(user);
        return ApiResponse.builder()
                .message("Registered Successfully")
                .statusCode(0)
                .data(user)
                .build();
    }


    public ApiResponse<?> getUserByUserName(String username) {
        var user = userRepoJpa.findByUsername(username).orElse(null);
        return ApiResponse.builder()
                .data(user)
                .statusCode(user != null ? 0 : 1)
                .build();
    }

//    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<?> findAllUser() {
        LOG.info("---> Finding all users : ");
        return ApiResponse.builder()
                .statusCode(0)
                .data(userRepoJpa.findAll())
                .build();
    }

    public ApiResponse<?> authenticate(UserLoginRequest request) {
        ApiResponse<UserLoginResponse> response = new ApiResponse<>();
        String messageError = request.validate();
        if (StringUtils.hasLength(messageError)) {
            response.setMessageFailed(messageError);
            return response;
        }
        String userName = request.getUserName();
        String password = request.getPassword();
        var user = userRepoJpa.findByUsername(userName).orElse(null);
        if (user == null) {
            response.setMessageFailed("User not found");
            return response;
        }
        boolean isMatched = passwordEncoder.matches(password, user.getPasswordHash());
        if (isMatched) {
            response.setDataSuccess(
                    UserLoginResponse.builder()
                            .token(jwtUtil.generateToken(userName))
                            .build()
            );
        }
        return response;
    }
}
