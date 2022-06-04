package com.tempter.edms_rest_api.dto;

import com.tempter.edms_rest_api.entity.enums.ERole;
import lombok.Data;

import java.util.Set;

@Data
public class UserDocDTO {

    private int id;
    private String username;
    private String email;

    private Set<DocumentDTO> signedDocuments;
    private Set<DocumentDTO> assignedDocuments;

    private ERole role;
    private String name;
    private String lastname;
    private String department;
}
