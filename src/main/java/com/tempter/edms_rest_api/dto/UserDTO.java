package com.tempter.edms_rest_api.dto;

import com.tempter.edms_rest_api.entity.enums.ERole;
import lombok.Data;

@Data
public class UserDTO {

    private int id;

    private String username;

    private String email;
    private ERole role;

    private String name;
    private String lastname;
    private String department;

}
