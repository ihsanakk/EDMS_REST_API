package com.tempter.edms_rest_api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SignatureDTO {

    private int id;

    private String signatureText;

    private Date signedAt;
}
