package com.example.isdbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ErrorDTO {

    private final String errorType;
    private final String message;

}
