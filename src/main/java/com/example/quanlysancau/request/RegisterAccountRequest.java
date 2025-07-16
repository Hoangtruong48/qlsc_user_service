package com.example.quanlysancau.request;

import com.example.quanlysancau.entity.User;
import com.example.quanlysancau.util.Mappable;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterAccountRequest implements Mappable<User> {
    String userName;
    String password;
    String email;
    String phone;
    Integer role;
    Integer status;

    @Override
    public User toEntity() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        User user = new User();
        user.setUsername(this.getUserName());
        user.setPasswordHash(passwordEncoder.encode(this.getPassword()));
        user.setEmail(this.getEmail());
        user.setPhone(this.getPhone());
        user.setRole(this.getRole());
        user.setStatus(this.getStatus());
        return user;
    }

    public String validate() {
        if (!StringUtils.hasLength(userName)) {
            return "Username is required";
        }
        if (!StringUtils.hasLength(password)) {
            return "Password is required";
        }
        if (!StringUtils.hasLength(phone)) {
            return "Phone number is required";
        }
        return null;
    }
}
