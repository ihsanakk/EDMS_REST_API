package com.tempter.edms_rest_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.tempter.edms_rest_api.entity.enums.DocumentStatus;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class DocumentDTO {

    private int documentId;

    private String documentName;

    private Date createdAt;

    @JsonIgnore
    private byte[] document;

    private UserDTO publisherUser;

    private DocumentStatus documentStatus;

    private String currentDepartment;

    private Set<SignatureDTO> signatureSet;

    private Set<UserDTO> signedUsers;

    private Set<UserDTO> assignedUsers;

}
