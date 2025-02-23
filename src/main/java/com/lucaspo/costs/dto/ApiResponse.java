package com.lucaspo.costs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse<T> {
    private String message;
    private T data;
    private String type;

    public ApiResponse(String message, String type) {
        this.message = message;
        this.data = null;
        this.type = type;
    }

    public ApiResponse(String message, String type, T data) {
        this.message = message;
        this.data = data;
        this.type = type;
    }
}
