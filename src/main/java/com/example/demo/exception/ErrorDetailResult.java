package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDetailResult {
    private String errorMessage;
    private Map<String,String> details;

    public ErrorDetailResult(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
