package com.tv.show.teleservice.api.exception;

import java.util.List;

import lombok.Data;

@Data
public class RestErrorResponse {

    private String errorCode;

    private List<String> messages;

    public RestErrorResponse(String errorCode, List<String> messages) {
        this.errorCode = errorCode;
        this.messages = messages;
    }
}
