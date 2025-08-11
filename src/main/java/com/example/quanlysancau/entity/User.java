package com.example.quanlysancau.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Arrays;

@Entity
@Table(name = "users", schema = "badminton")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Getter
    public enum UserRole {
        ADMIN(1), STAFF(2), CUSTOMER(3);

        private final int code;

        UserRole(int code) {
            this.code = code;
        }

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = UserNameConstant.USER_ID)
    private Long userId;
    
    @Column(name = UserNameConstant.USERNAME,nullable = false, unique = true, length = 50)
    private String username;
    
    @Column(name = UserNameConstant.PASSWORD_HASH, nullable = false, length = 255)
    private String passwordHash;
    
    @Column(length = 100)
    private String email;
    
    @Column(name = UserNameConstant.FULL_NAME, length = 100)
    private String fullName;
    
    @Column(name = UserNameConstant.PHONE,length = 20)
    private String phone;
    
    @Column(name = UserNameConstant.ROLE,nullable = false)
    private Integer role = UserRole.CUSTOMER.getCode();
    
    @Column(name = UserNameConstant.STATUS, nullable = false)
    private Integer status = 1;
    
    @Column(name = UserNameConstant.CREATED_AT, updatable = false)
    private Long createdAt = System.currentTimeMillis();
    
    @Column(name = UserNameConstant.UPDATED_AT)
    private Long updatedAt = System.currentTimeMillis();

    // Converter để xử lý enum
    @Converter(autoApply = true)
    public static class UserRoleConverter implements AttributeConverter<UserRole, Integer> {
        @Override
        public Integer convertToDatabaseColumn(UserRole role) {
            return role.getCode();
        }

        @Override
        public UserRole convertToEntityAttribute(Integer code) {
            return Arrays.stream(UserRole.values())
                .filter(r -> r.getCode() == code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        }
    }
    public static class UserNameConstant {
        // Tên bảng và schema
        public static final String TABLE_NAME = "users";
        public static final String SCHEMA = "badminton";

        // Tên các cột
        public static final String USER_ID = "user_id";
        public static final String USERNAME = "username";
        public static final String PASSWORD_HASH = "password_hash";
        public static final String EMAIL = "email";
        public static final String FULL_NAME = "full_name";
        public static final String PHONE = "phone";
        public static final String ROLE = "role";
        public static final String STATUS = "status";
        public static final String CREATED_AT = "created_at";
        public static final String UPDATED_AT = "updated_at";
        // trang thai tai khoan
        public static final int STATUS_ON = 1;
        public static final int STATUS_OFF = 2;

    }
}