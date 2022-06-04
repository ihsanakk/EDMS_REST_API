package com.tempter.edms_rest_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageResponse {

    private boolean error;
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}
