package com.botree.csng.bundleproduct.dto;

import lombok.Data;

@Data
public class ApiResponse {
    private String message;
    private int status;

    public ApiResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }
}