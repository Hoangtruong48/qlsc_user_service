package com.example.quanlysancau.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiResponse<T> {
    int statusCode;
    String message;
    T data;

    public void setSuccess() {
        this.statusCode = 0;
        message = "success";
    }

    public void setError() {
        this.statusCode = -1;
        message = "Action failed";
    }

    public void setMessageFailed(String message) {
        this.statusCode = -1;
        this.message = message;
    }

    public void setMessageSuccess(String message) {
        this.statusCode = 0;
        this.message = message;
    }


}
