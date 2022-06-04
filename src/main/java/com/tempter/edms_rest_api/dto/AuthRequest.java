package com.tempter.edms_rest_api.dto;

import lombok.Data;

@Data
public class AuthRequest {

    private String username;
    private String email;
    private String password;

    private String name;
    private String lastname;
    private String department;
}
